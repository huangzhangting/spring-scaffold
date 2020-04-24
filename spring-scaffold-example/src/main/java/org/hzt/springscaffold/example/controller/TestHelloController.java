package org.hzt.springscaffold.example.controller;

import org.hzt.springscaffold.example.service.HelloServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("test/")
public class TestHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloServiceFeign helloServiceFeign;


    @GetMapping("hello")
    public Object testHello(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-service-user");
        String uri = serviceInstance.getUri().toString();
        String method = "/hello";
        String result = restTemplate.getForObject(uri + method, String.class);
        return result;
    }

    @GetMapping("hello2")
    public Object testHello2(){
        return helloServiceFeign.hello();
    }

}
