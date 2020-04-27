package org.hzt.springscaffold.example.service;

import org.hzt.springscaffold.example.vo.UserVO;
import org.springframework.stereotype.Component;

/**
 * 服务降级、熔断
 * */
@Component
public class HelloServiceFallback implements HelloServiceFeign {
    @Override
    public String hello() {
        return "hello fallback";
    }

    @Override
    public UserVO postUser(String name) {
        UserVO userVO = new UserVO();
        userVO.setName(name);
        userVO.setId(-9);
        return userVO;
    }

    @Override
    public UserVO postUser(UserVO param) {
        UserVO userVO = new UserVO();
        userVO.setName(param.getName());
        userVO.setAge(param.getAge());
        userVO.setId(-9);
        return userVO;
    }

}
