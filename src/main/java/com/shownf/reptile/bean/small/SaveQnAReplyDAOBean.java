package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.repository.qna.QnAReplyRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnAReplyDAOBean {

    QnAReplyRepositoryJPA qnAReplyRepositoryJPA;

    @Autowired
    public SaveQnAReplyDAOBean(QnAReplyRepositoryJPA qnAReplyRepositoryJPA) {
        this.qnAReplyRepositoryJPA = qnAReplyRepositoryJPA;
    }

    // QnA 대댓글 저장
    public void exec(QnAReplyDAO qnAReplyDAO){
        qnAReplyRepositoryJPA.save(qnAReplyDAO);
    }
}
