package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.repository.qna.QnAReplyRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnAReplyDAOBean {

    QnAReplyRepositoryJPA qnAReplyRepositoryJPA;

    @Autowired
    public GetQnAReplyDAOBean(QnAReplyRepositoryJPA qnAReplyRepositoryJPA) {
        this.qnAReplyRepositoryJPA = qnAReplyRepositoryJPA;
    }

    // 댓글에 해당하는 대댓글 조회
    public QnAReplyDAO exec(Long qnaReplyId){
        return qnAReplyRepositoryJPA.findById(qnaReplyId).orElse(null);
    }
}
