package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.service.qna.QnAReplyService;
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
public class QnAReplyController {

    QnAReplyService qnaReplyService;

    @Autowired
    public QnAReplyController(QnAReplyService qnaReplyService) {
        this.qnaReplyService = qnaReplyService;
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
}
