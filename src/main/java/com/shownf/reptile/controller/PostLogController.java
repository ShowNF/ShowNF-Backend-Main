package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.ResponseMetaGetDTO;
import com.shownf.reptile.service.PostLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class PostLogController {

    PostLogService postLogService;

    @Autowired
    public PostLogController(PostLogService postLogService) {
        this.postLogService = postLogService;
    }

    // 최근 조회 게시물 Id list 조회
    @ApiOperation(value = "최근 조회 게시물 Id list 조회", notes = "유저 아이디로 최근 조회 게시물 Id list 조회")
    @GetMapping("postLog/user/{userId}")
    public List<ResponseMetaGetDTO> getPost(@PathVariable Long userId){
        return postLogService.getPostLogs(userId);
    }

}
