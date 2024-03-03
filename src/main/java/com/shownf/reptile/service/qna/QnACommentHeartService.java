package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.bean.SaveQnACommentHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnACommentHeartService {

    SaveQnACommentHeartBean saveQnACommentHeartBean;

    @Autowired
    public QnACommentHeartService(SaveQnACommentHeartBean saveQnACommentHeartBean) {
        this.saveQnACommentHeartBean = saveQnACommentHeartBean;
    }

    // QnA 댓글 좋아요
    public Long saveQnACommentHeart(RequestQnACommentHeartSaveDTO requestQnACommentHeartSaveDTO) {
        return saveQnACommentHeartBean.exec(requestQnACommentHeartSaveDTO);
    }
}
