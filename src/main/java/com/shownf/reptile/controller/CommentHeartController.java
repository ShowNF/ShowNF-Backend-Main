package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.service.CommentHeartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CommentHeartController {

    CommentHeartService commentHeartService;

    @Autowired
    public CommentHeartController(CommentHeartService commentHeartService) {
        this.commentHeartService = commentHeartService;
    }

    // 좋아요 누른 댓글 아이디 전체 조회
    @ApiOperation(value = "댓글 아이디 전체 조회", notes = "좋아요 누른 댓글 아이디 전체 조회")
    @GetMapping("commentHeart/user/{userId}")
    public List<Long> getCommentIds(@PathVariable Long userId){
        return commentHeartService.getCommentIds(userId);
    }

    // 댓글 좋아요 추가
    @ApiOperation(value = "댓글 좋아요 저장", notes = "댓글에 좋아요를 누를시 저장한다.")
    @PostMapping("commentHeart")
    public ResponseEntity<Map<String, Object>> saveCommentHeart(@RequestBody RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){
        Long commentHeartId = commentHeartService.saveCommentHeart(requestCommentHeartSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (commentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentHeartId != null) ? "Save Success" : "Save Fail");
        requestMap.put("commentHeartId", commentHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }


    // 댓글 좋아요 삭제
    @ApiOperation(value = "댓글 좋아요 삭제", notes = "댓글에 좋아요를 누를시 삭제한다.")
    @DeleteMapping("commentHeart")
    public ResponseEntity<Map<String, Object>> deleteCommentHeart(@RequestBody RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){
        Long commentHeartId = commentHeartService.deleteCommentHeart(requestCommentHeartDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentHeartId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("commentHeartId", commentHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
