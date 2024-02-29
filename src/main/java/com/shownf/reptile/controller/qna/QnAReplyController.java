package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAReplyGetDTO;
import com.shownf.reptile.service.qna.QnAReplyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class QnAReplyController {

    QnAReplyService qnaReplyService;

    @Autowired
    public QnAReplyController(QnAReplyService qnaReplyService) {
        this.qnaReplyService = qnaReplyService;
    }

    // QnA 대댓글 전체 조회
    @ApiOperation(value = "QnA 대댓글 전체 조회", notes = "QnA댓글아이디로 찾은 대댓글 전체 조회")
    @GetMapping("qna/reply/comment/{qnaCommentId}")
    public List<ResponseQnAReplyGetDTO> getQnAReplys(@PathVariable Long qnaCommentId){
        return qnaReplyService.getQnAReplys(qnaCommentId);
    }

    // QnA 대댓글 저장
    @ApiOperation(value = "QnA 대댓글 저장", notes = "QnA게시판에 댓글에 대댓글 작성시 저장")
    @PostMapping("qna/reply")
    public ResponseEntity<Map<String, Object>> saveQnAReply(@RequestBody RequestQnAReplySaveDTO requestQnAReplySaveDTO){
        Long qnaReplyId = qnaReplyService.saveQnAReply(requestQnAReplySaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaReplyId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaReplyId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaReplyId", qnaReplyId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA 대댓글 수정
    @ApiOperation(value = "QnA 대댓글 수정", notes = "QnA게시판에 댓글에 대댓글 수정시 저장")
    @PutMapping("qna/reply")
    public ResponseEntity<Map<String, Object>> updateQnAReply(@RequestBody RequestQnAReplyUpdateDTO requestQnAReplyUpdateDTO, HttpServletRequest request){
        Long qnaReplyId = qnaReplyService.updateQnAReply(requestQnAReplyUpdateDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaReplyId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaReplyId != null) ? "Update Success" : "Update Fail");
        requestMap.put("qnaReplyId", qnaReplyId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA 대댓글 삭제
    @ApiOperation(value = "QnA 대댓글 삭제", notes = "QnA게시판에 댓글에 대댓글 삭제시 저장")
    @DeleteMapping("qna/reply")
    public ResponseEntity<Map<String, Object>> deleteQnAReply(@RequestBody RequestQnAReplyDeleteDTO requestQnAReplyDeleteDTO, HttpServletRequest request){
        Long qnaReplyId = qnaReplyService.deleteQnAReply(requestQnAReplyDeleteDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaReplyId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaReplyId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("qnaReplyId", qnaReplyId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
