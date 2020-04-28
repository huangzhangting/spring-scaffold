package org.hzt.springscaffold.user.controller;

import org.hzt.springscaffold.user.dal.mapper.UserMapper;
import org.hzt.springscaffold.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("hello")
    public Object hello(){
        return "hello consul, from " + port + " userCount:" + userMapper.count();
    }

    @PostMapping("param/postUser")
    public UserVO postUser(@RequestParam("name")String name){
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setName(name);
        return userVO;
    }

    @PostMapping("body/postUser")
    public UserVO postUser(@RequestBody UserVO param){
        UserVO userVO = new UserVO();
        userVO.setId(2);
        userVO.setName(param.getName());
        userVO.setAge(param.getAge());
        return userVO;
    }

}
