package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.service.qna.QnACommentService;
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
public class QnACommentController {

    QnACommentService qnaCommentService;

    @Autowired
    public QnACommentController(QnACommentService qnaCommentService) {
        this.qnaCommentService = qnaCommentService;
    }

    // QnA Comment 저장
    @ApiOperation(value = "QnA 댓글 저장", notes = "QnA댓글 작성시 저장한다.")
    @PostMapping("qna/comment")
    public ResponseEntity<Map<String, Object>> saveQnAComment(@RequestBody RequestQnACommentSaveDTO requestCommentSaveDTO){
        Long qnaCommentId = qnaCommentService.saveQnAComment(requestCommentSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaCommentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaCommentId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaCommentId", qnaCommentId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}