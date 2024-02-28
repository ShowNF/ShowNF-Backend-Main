package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.repository.QnACommentRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnACommentDAOBean {

    QnACommentRepositoryJPA qnACommentRepositoryJPA;

    @Autowired
    public GetQnACommentDAOBean(QnACommentRepositoryJPA qnACommentRepositoryJPA) {
        this.qnACommentRepositoryJPA = qnACommentRepositoryJPA;
    }

    // QnA 댓글 가져오기
    public QnACommentDAO exec(Long qnaCommentId){
        return qnACommentRepositoryJPA.findById(qnaCommentId).orElse(null);
    }
}
