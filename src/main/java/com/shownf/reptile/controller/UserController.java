package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestSiteUserUpdateDTO;
import com.shownf.reptile.Model.DTO.ResponseUserDTO;
import com.shownf.reptile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    // handle 아이디로 유저 객체 찾기
    @GetMapping("user/{userId}")
    public ResponseUserDTO getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    // 유저 프로필 변경
    @PutMapping("user")
    public ResponseEntity<Map<String, Object>> updateSiteUser(@RequestBody RequestSiteUserUpdateDTO requestSiteUserUpdateDTO, HttpServletRequest request){
        Long handleId = userService.updateUserSiteName(requestSiteUserUpdateDTO, request);

        // HTTP 상태 반환
        HttpStatus httpStatus = (handleId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (handleId != null) ? "Update Success" : "Update Fail");
        requestMap.put("handleId", handleId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
