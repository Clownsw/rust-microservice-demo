package me.liangdi.cloud.rustmicroserviceconsumer.service;

import me.liangdi.cloud.rustmicroserviceconsumer.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@FeignClient(name = "rust-microservice", fallback = OpenFeignRustService.FallbackService.class)
public interface OpenFeignRustService {
    @GetMapping("/foo")
    String foo();

    @GetMapping("/bar")
    Message bar();

    @Component
    class FallbackService implements OpenFeignRustService {

        @Override
        public String foo() {
            return "foo fallback";
        }

        @Override
        public Message bar() {
            return new Message();
        }
    }
}

