package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.repository.qna.QnACommentHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteQnACommentHeartDAOBean {

    QnACommentHeartRepositoryJPA qnACommentHeartRepositoryJPA;

    @Autowired
    public DeleteQnACommentHeartDAOBean(QnACommentHeartRepositoryJPA qnACommentHeartRepositoryJPA) {
        this.qnACommentHeartRepositoryJPA = qnACommentHeartRepositoryJPA;
    }

    // QnA 댓글 좋아요 삭제
    public void exec(QnACommentHeartDAO qnACommentHeartDAO) {
        qnACommentHeartRepositoryJPA.delete(qnACommentHeartDAO);
    }
}
