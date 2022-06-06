use actix_web::{web, App, Error, HttpResponse, HttpServer, Responder};
use serde::{Deserialize, Serialize};

static NACOS_SERVER: &str = "http://127.0.0.1:8848/nacos";
static PROVIDER_NAME: &str = "rust-microservice";
static PROVIDER_HOST: &str = "127.0.0.1";
static PROVIDER_PORT: i32 = 8080;

mod nacos;

#[derive(Serialize, Deserialize)]
struct Message {
    msg: String,
    code: i32,
}

async fn index() -> impl Responder {
    HttpResponse::Ok().body("this is a rust microservice demo")
}

async fn plain_text_resp() -> impl Responder {
    HttpResponse::Ok().body("from /foo")
}

async fn json_resp() -> impl Responder {
    let p = Message {
        msg: "from /bar".to_string(),
        code: 0,
    };

    HttpResponse::Ok().json(p)
}

pub async fn init_nacos() {
    println!("listening at http://localhost:8080");

    nacos::register_service();

    std::thread::spawn(|| {
        nacos::ping_schedule();
    });
}

#[actix_web::main]
async fn main() -> Result<(), Error> {
    init_nacos().await;

    HttpServer::new(|| {
        App::new()
            .route("/", web::get().to(index))
            .route("/foo", web::get().to(plain_text_resp))
            .route("/bar", web::get().to(json_resp))
    })
    .bind("127.0.0.1:8080")
    .unwrap()
    .run()
    .await?;

    Ok(())
}
