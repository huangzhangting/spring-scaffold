package org.hzt.springscaffold.user.controller;

import org.hzt.springscaffold.user.vo.UserVO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("hello")
    public Object hello(){
        return "hello consul";
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
