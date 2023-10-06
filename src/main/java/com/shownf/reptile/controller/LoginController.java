package com.shownf.reptile.controller;

import com.shownf.reptile.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> socialLogin(@RequestParam String code, @PathVariable String registrationId) {
        String check = loginService.socialLogin(code, registrationId);

        // HTTP 상태 변환
        HttpStatus httpStatus = (check != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (check != null) ? "Save Success" : "Save Fail");
        requestMap.put("check", check);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
