package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.repository.qna.QnACommentHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnACommentHeartDAOBean {

    QnACommentHeartRepositoryJPA qnACommentHeartRepositoryJPA;

    @Autowired
    public GetQnACommentHeartDAOBean(QnACommentHeartRepositoryJPA qnACommentHeartRepositoryJPA) {
        this.qnACommentHeartRepositoryJPA = qnACommentHeartRepositoryJPA;
    }

    // QnA 댓글 좋아요 객체 가져오기
    public QnACommentHeartDAO exec(RequestQnACommentHeartSaveDTO requestQnACommentHeartSaveDTO){
        return qnACommentHeartRepositoryJPA.findByQnaCommentIdAndUserId(requestQnACommentHeartSaveDTO.getQnaCommentId(), requestQnACommentHeartSaveDTO.getUserId());
    }
}
