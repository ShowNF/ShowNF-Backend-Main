package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    // handle 아이디로 유저 이름 반환
    @GetMapping("user/{userId}/name")
    public ResponseEntity<String> getUserName(@PathVariable Long userId){
        String userName = userService.getUserName(userId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userName);
    }

    // handle 아이디로 유저 이미지 반환
    @GetMapping("user/{userId}/image")
    public String getUserImage(@PathVariable Long userId){
        String userImage = userService.getUserImage(userId);
        return userImage;
    }

    // 내 팔로우 리스트 조회
    @GetMapping("user/followers/{userId}")
    public List<ResponseFollowerDTO> getFollowersUser(@PathVariable Long userId){
        return userService.getFollowersUser(userId);
    }

    // 내 팔로잉 리스트 조회
    @GetMapping("user/followings/{userId}")
    public List<ResponseFollowingDTO> getFollowingsUser(@PathVariable Long userId){
        return userService.getFollowingsUser(userId);
    }

    // 팔로우 추가 기능
    @PostMapping("user/follow")
    public ResponseEntity<Map<String, Object>> followUser(@RequestBody RequestFollowDTO requestFollowDTO){
        Long followId = userService.followUser(requestFollowDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (followId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (followId != null) ? "Save Success" : "Save Fail");
        requestMap.put("followId", followId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 유저 프로필 변경
    @PutMapping("user")
    public ResponseEntity<Map<String, Object>> updateSiteUser(@RequestBody RequestSiteUserUpdateDTO requestSiteUserUpdateDTO, HttpServletRequest request){
        Long userId = userService.updateUserSiteName(requestSiteUserUpdateDTO, request);

        // HTTP 상태 반환
        HttpStatus httpStatus = (userId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (userId != null) ? "Update Success" : "Update Fail");
        requestMap.put("userId", userId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 팔로우 취소 기능
    @DeleteMapping("user/follow")
    public ResponseEntity<Map<String, Object>> deleteFollowUser(@RequestBody RequestFollowDTO requestFollowDTO){
        Long followId = userService.deleteFollowUser(requestFollowDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (followId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (followId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("followId", followId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
