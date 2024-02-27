package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.service.qna.QnAPostHeartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class QnAPostHeartController {

    QnAPostHeartService qnAPostHeartService;

    @Autowired
    public QnAPostHeartController(QnAPostHeartService qnAPostHeartService) {
        this.qnAPostHeartService = qnAPostHeartService;
    }

    // QnA 게시물 좋아요 추가
    @ApiOperation(value = "QnA 게시물 좋아요 저장", notes = "QnA 게시물에 좋아요를 누를시 저장한다.")
    @PostMapping("qna/postHeart")
    public ResponseEntity<Map<String, Object>> savePostHeart(@RequestBody RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){
        Long qnaPostHeartId = qnAPostHeartService.saveQnAPostHeart(requestQnAPostHeartSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaPostHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaPostHeartId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaPostHeartId", qnaPostHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA 게시물 좋아요 삭제
    @ApiOperation(value = "QnA 게시물 좋아요 삭제", notes = "QnA 게시물에 좋아요를 누를시 삭제한다.")
    @DeleteMapping("qna/postHeart")
    public ResponseEntity<Map<String, Object>> deletePostHeart(@RequestBody RequestQnAPostHeartDeleteDTO requestQnAPostHeartDeleteDTO){
        Long qnaPostHeartId = qnAPostHeartService.deleteQnAPostHeart(requestQnAPostHeartDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (qnaPostHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaPostHeartId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("qnaPostHeartId", qnaPostHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
