package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.service.qna.QnACommentHeartService;
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
public class QnACommentHeartController {

    QnACommentHeartService qnACommentHeartService;

    @Autowired
    public QnACommentHeartController(QnACommentHeartService qnACommentHeartService) {
        this.qnACommentHeartService = qnACommentHeartService;
    }

    // 좋아요 누른 QnA 댓글 아이디 전체 조회
    @ApiOperation(value = "QnA 댓글 아이디 전체 조회", notes = "좋아요 누른 QnA 댓글 아이디 전체 조회")
    @GetMapping("qna/commentHeart/user/{userId}")
    public List<Long> getQnACommentIds(@PathVariable Long userId){
        return qnACommentHeartService.getQnACommentIds(userId);
    }

    // QnA 댓글 좋아요
    @ApiOperation(value = "QnA 댓글 좋아요 저장", notes = "QnA 댓글에 좋아요를 누를시 저장한다.")
    @PostMapping("qna/commentHeart")
    public ResponseEntity<Map<String, Object>> saveQnACommentHeart(@RequestBody RequestQnACommentHeartSaveDTO requestQnACommentHeartSaveDTO){
        Long qnaCommentHeartId = qnACommentHeartService.saveQnACommentHeart(requestQnACommentHeartSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaCommentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaCommentHeartId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaCommentHeartId", qnaCommentHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA 댓글 좋아요 삭제
    @ApiOperation(value = "QnA 댓글 좋아요 삭제", notes = "QnA 댓글에 좋아요를 누를시 삭제한다.")
    @DeleteMapping("qna/commentHeart")
    public ResponseEntity<Map<String, Object>> deleteQnACommentHeart(@RequestBody RequestQnACommentHeartDeleteDTO requestQnACommentHeartDeleteDTO, HttpServletRequest request){
        Long qnaCommentHeartId = qnACommentHeartService.deleteQnACommentHeart(requestQnACommentHeartDeleteDTO, request);

        // HTTP 상태 반환
        HttpStatus httpStatus = (qnaCommentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaCommentHeartId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("qnaCommentHeartId", qnaCommentHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
