package org.hzt.springscaffold.example.service;

import org.hzt.springscaffold.example.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-service-user", fallback = HelloServiceFallback.class)
public interface HelloServiceFeign {

    @GetMapping("hello")
    String hello();

    @PostMapping("param/postUser")
    UserVO postUser(@RequestParam("name")String name);

    @PostMapping("body/postUser")
    UserVO postUser(@RequestBody UserVO param);

}
