package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.repository.qna.QnAPostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostDAOBean {

    QnAPostRepositoryJPA qnAPostRepositoryJPA;

    @Autowired
    public GetQnAPostDAOBean(QnAPostRepositoryJPA qnAPostRepositoryJPA) {
        this.qnAPostRepositoryJPA = qnAPostRepositoryJPA;
    }

    // QnA 게시물 조회
    public QnAPostDAO exec(Long qnaPostId){
        return qnAPostRepositoryJPA.findById(qnaPostId).orElse(null);
    }
}
