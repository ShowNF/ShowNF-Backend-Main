package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.repository.QnACommentRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnACommentDAOBean {

    QnACommentRepositoryJPA qnACommentRepositoryJPA;

    @Autowired
    public SaveQnACommentDAOBean(QnACommentRepositoryJPA qnACommentRepositoryJPA) {
        this.qnACommentRepositoryJPA = qnACommentRepositoryJPA;
    }

    // QnA Comment 저장
    public void exec(QnACommentDAO qnACommentDAO){
        qnACommentRepositoryJPA.save(qnACommentDAO);
    }
}