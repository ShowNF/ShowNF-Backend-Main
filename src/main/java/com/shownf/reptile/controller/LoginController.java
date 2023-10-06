package com.shownf.reptile.controller;

import com.shownf.reptile.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {

    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /*//구글
    @GetMapping("/login/oauth2/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        loginService.socialLogin(code, registrationId);
    }

    //카카오
    @GetMapping("/login/oauth2/kakao")
    public void kakaoCalllback(@RequestParam String code) {
        System.out.println("code : " + code);
        loginService.getKakaoAccessToken(code);
    }*/

    @GetMapping("/login/oauth2/{registrationId}")
    public String socialLogin(@RequestParam String code, @PathVariable String registrationId) {
        loginService.socialLogin(code, registrationId);
        return "ok";
    }
}
