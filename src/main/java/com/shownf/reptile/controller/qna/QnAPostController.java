package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.service.qna.QnAPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class QnAPostController {

    QnAPostService qnAPostService;

    @Autowired
    public QnAPostController(QnAPostService qnAPostService) {
        this.qnAPostService = qnAPostService;
    }

    // QnA 게시물 저장
    @ApiOperation(value = "QnA 게시물 저장", notes = "QnA 게시물 작성시 저장")
    @PostMapping("qna/post")
    public ResponseEntity<Map<String, Object>> saveQnAPost(@RequestBody RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        Long qnaPostId = qnAPostService.saveQnAPostDAO(requestQnAPostSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaPostId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaPostId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaPostId", qnaPostId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
