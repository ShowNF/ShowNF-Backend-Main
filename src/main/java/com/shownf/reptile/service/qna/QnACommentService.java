package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.bean.SaveQnACommentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnACommentService {

    SaveQnACommentBean saveQnACommentBean;

    @Autowired
    public QnACommentService(SaveQnACommentBean saveQnACommentBean) {
        this.saveQnACommentBean = saveQnACommentBean;
    }

    // QnA Comment 저장
    public Long saveQnAComment(RequestQnACommentSaveDTO requestQnACommentSaveDTO){
        return saveQnACommentBean.exec(requestQnACommentSaveDTO);
    }
}
