package com.shownf.reptile.controller;

import com.shownf.reptile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 토큰으로 유저 아이디 찾기
    @GetMapping("user/token/{token}")
    public Long getUserId(@PathVariable String token){
        return userService.getUserId(token);
    }
}
