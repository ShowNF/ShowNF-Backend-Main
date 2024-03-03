package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import com.shownf.reptile.repository.qna.QnAReplyHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteQnAReplyHeartDAOBean {

    QnAReplyHeartRepositoryJPA qnAReplyHeartRepositoryJPA;

    @Autowired
    public DeleteQnAReplyHeartDAOBean(QnAReplyHeartRepositoryJPA qnAReplyHeartRepositoryJPA) {
        this.qnAReplyHeartRepositoryJPA = qnAReplyHeartRepositoryJPA;
    }

    // QnA 대댓글 좋아요 삭제
    public void exec(QnAReplyHeartDAO qnAReplyHeartDAO){
        qnAReplyHeartRepositoryJPA.delete(qnAReplyHeartDAO);
    }
}
