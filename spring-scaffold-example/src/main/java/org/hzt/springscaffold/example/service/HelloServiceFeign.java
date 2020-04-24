package org.hzt.springscaffold.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "consul-service-user")
public interface HelloServiceFeign {

    @GetMapping("hello")
    String hello();

}
