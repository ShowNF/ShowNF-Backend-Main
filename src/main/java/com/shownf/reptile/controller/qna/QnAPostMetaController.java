package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.service.qna.QnAPostMetaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class QnAPostMetaController {

    QnAPostMetaService qnAPostMetaService;

    @Autowired
    public QnAPostMetaController(QnAPostMetaService qnAPostMetaService) {
        this.qnAPostMetaService = qnAPostMetaService;
    }

    // QnA postId로 meta data 조회
    @ApiOperation(value = "QnA post meta data 조회", notes = "qnaPostId로 meta data 조회")
    @GetMapping("qna/postMeta/{qnaPostId}")
    public ResponseQnAPostMetaDTO getQnAPostMeta(@PathVariable Long qnaPostId){
        return qnAPostMetaService.getQnAPostMeta(qnaPostId);
    }

}
