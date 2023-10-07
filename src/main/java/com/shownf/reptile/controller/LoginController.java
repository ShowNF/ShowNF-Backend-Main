package com.shownf.reptile.controller;

import com.shownf.reptile.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        try {
            String check = loginService.socialLogin(code, registrationId);

            // HTTP 상태 반환
            HttpStatus httpStatus = (check != null) ? HttpStatus.PERMANENT_REDIRECT : HttpStatus.INTERNAL_SERVER_ERROR;

            // 메시지와 id 값 json 데이터로 반환
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("message", (check != null) ? "Login Success" : "Login Fail");
            requestMap.put("id", check);

            // 헤더 추가 및 Redirect:
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("http://shownf.s3-website.ap-northeast-2.amazonaws.com/"));

            return ResponseEntity.status(httpStatus).headers(headers).body(requestMap);
        } catch (Exception e) {
            // 예외가 발생한 경우 처리
            e.printStackTrace(); // 에러 내용 로깅

            // 에러 응답 반환
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }
    }
}
