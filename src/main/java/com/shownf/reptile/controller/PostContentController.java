package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestPostContentUpdateDTO;
import com.shownf.reptile.service.PostContentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class PostContentController {

    PostContentService postContentService;

    @Autowired
    public PostContentController(PostContentService postContentService) {
        this.postContentService = postContentService;
    }

    // 게시물 내용 수정
    @ApiOperation(value = "게시물 내용 수정", notes = "게시물 내용 수정시 저장")
    @PutMapping("postContent")
    public ResponseEntity<Map<String, Object>> updatePostContent(@RequestBody RequestPostContentUpdateDTO requestPostContentUpdateDTO){
        Long postContentId = postContentService.updatePostContent(requestPostContentUpdateDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (postContentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (postContentId != null) ? "Update Success" : "Update Fail");
        requestMap.put("postContentId", postContentId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
