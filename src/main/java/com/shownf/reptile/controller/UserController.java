package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    // token valid check
    @ApiOperation(value = "토큰 갱신 확인", notes = "토큰이 유효한지 판단함")
    @GetMapping("user/{userId}/token")
    public Boolean checkToken(@PathVariable Long userId, HttpServletRequest request){
        return userService.checkToken(userId, request);
    }

    // 토큰으로 유저 아이디 찾기
    @ApiOperation(value = "토큰으로 유저 아이디 찾기", notes = "토큰을 통해서 접속한 유저 아이디 찾음")
    @GetMapping("user/token/{token}")
    public Long getUserId(@PathVariable String token){
        return userService.getUserId(token);
    }

    // handle 아이디로 유저 객체 찾기
    @ApiOperation(value = "handle 아이디로 유저 객체 찾기", notes = "유저 객체 찾기")
    @GetMapping("user/{userId}")
    public ResponseUserDTO getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    // handle 아이디로 유저 이름 반환
    @ApiOperation(value = "handle 아이디로 유저 이름 찾기", notes = "유저 이름 확인하기")
    @GetMapping("user/{userId}/name")
    public ResponseEntity<String> getUserName(@PathVariable Long userId){
        String userName = userService.getUserName(userId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(userName);
    }

    // handle 아이디로 유저 이미지 반환
    @ApiOperation(value = "handle 아이디로 유저 이미지 찾기", notes = "유저 이미지 확인하기")
    @GetMapping("user/{userId}/image")
    public String getUserImage(@PathVariable Long userId){
        return userService.getUserImage(userId);
    }

    // 추천 계정 조회
    @ApiOperation(value = "추천 계정 조회", notes = "유저에게 추천하는 계정 조회")
    @GetMapping("user/recommend")
    public List<ResponseRecommendUserGetDTO> getRecommendUser(){
        return userService.getRecommendUser();
    }

    // 유저 검색
    @ApiOperation(value = "유저 검색", notes = "유저 검색")
    @GetMapping("user/search/{search}")
    public List<ResponseRecommendUserGetDTO> getUserSearch(@PathVariable String search){
        return userService.getUserSearch(search);
    }

    // 내 팔로우 리스트 조회
    @ApiOperation(value = "팔로우 하고 있는 유저들 조회", notes = "유저의 팔로우 리스트 조회")
    @GetMapping("user/followers/{userId}")
    public List<ResponseFollowerDTO> getFollowersUser(@PathVariable Long userId){
        return userService.getFollowersUser(userId);
    }

    // 내 팔로잉 리스트 조회
    @ApiOperation(value = "나를 팔로우 하고 있는 유저들 조회", notes = "유저의 팔로잉 리스트 조회")
    @GetMapping("user/followings/{userId}")
    public List<ResponseFollowingDTO> getFollowingsUser(@PathVariable Long userId){
        return userService.getFollowingsUser(userId);
    }

    // 팔로우 추가 기능
    @ApiOperation(value = "팔로우 추가", notes = "팔로우 하기")
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
    @ApiOperation(value = "유저 프로필 수정", notes = "유저의 프로필 수정")
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
    @ApiOperation(value = "팔로우 취소", notes = "팔로우 취소하기")
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
