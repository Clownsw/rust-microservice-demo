package me.liangdi.cloud.rustmicroserviceconsumer.web;

import me.liangdi.cloud.rustmicroserviceconsumer.entity.Message;
import me.liangdi.cloud.rustmicroserviceconsumer.service.OpenFeignRustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rust-service/test")
@RestController
public class ConnectRustServiceController {

    @Autowired
    @Qualifier("me.liangdi.cloud.rustmicroserviceconsumer.service.OpenFeignRustService")
    private OpenFeignRustService openFeignRustService;

    @GetMapping("/foo")
    public String foo() {
        return openFeignRustService.foo();
    }

    @GetMapping("/bar")
    public Message bar() {
        return openFeignRustService.bar();
    }
}
