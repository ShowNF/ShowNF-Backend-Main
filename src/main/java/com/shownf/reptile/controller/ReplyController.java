package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplysDTO;
import com.shownf.reptile.service.ReplyService;
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
public class ReplyController {

    ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }


    // 대댓글 전체 조회
    @ApiOperation(value = "대댓글 전체 조회", notes = "댓글아이디로 찾은 대댓글 전체 조회")
    @GetMapping("reply/comment/{commentId}")
    public List<RequestReplysDTO> getReplys(@PathVariable Long commentId){
        return replyService.getReplys(commentId);
    }


    // 대댓글 저장
    @ApiOperation(value = "대댓글 저장", notes = "댓글에 댓글 작성시 저장")
    @PostMapping("reply")
    public ResponseEntity<Map<String, Object>> saveReply(@RequestBody RequestReplySaveDTO requestReplySaveDTO){
        Long replyId = replyService.saveReply(requestReplySaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (replyId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (replyId != null) ? "Save Success" : "Save Fail");
        requestMap.put("replyId", replyId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }


    // 대댓글 삭제
    @ApiOperation(value = "대댓글 삭제", notes = "댓글에 댓글 삭제시 삭제")
    @DeleteMapping("reply")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody RequestReplyDeleteDTO requestReplyDeleteDTO){
        Long replyId = replyService.deleteReply(requestReplyDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (replyId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (replyId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("replyId", replyId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
