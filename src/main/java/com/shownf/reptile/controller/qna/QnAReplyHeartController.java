package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.service.qna.QnAReplyHeartService;
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
public class QnAReplyHeartController {

    QnAReplyHeartService qnAReplyHeartService;

    @Autowired
    public QnAReplyHeartController(QnAReplyHeartService qnAReplyHeartService) {
        this.qnAReplyHeartService = qnAReplyHeartService;
    }

    // QnA 대댓글 좋아요 저장
    @ApiOperation(value = "QnA 대댓글 좋아요 저장", notes = "QnA 대댓글에 좋아요를 누를시 저장한다.")
    @PostMapping("qna/replyHeart")
    public ResponseEntity<Map<String, Object>> saveQnAReplyHeart(@RequestBody RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){
        Long qnaReplyHeartId = qnAReplyHeartService.saveQnAReplyHeart(requestQnAReplyHeartSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaReplyHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaReplyHeartId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaReplyHeartId", qnaReplyHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
