package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.repository.qna.QnAPostHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnAPostHeartDAOBean {

    QnAPostHeartRepositoryJPA qnAPostHeartRepositoryJPA;

    @Autowired
    public SaveQnAPostHeartDAOBean(QnAPostHeartRepositoryJPA qnAPostHeartRepositoryJPA) {
        this.qnAPostHeartRepositoryJPA = qnAPostHeartRepositoryJPA;
    }

    // QnA 게시물 좋아요 저장
    public void exec(QnAPostHeartDAO qnAPostHeartDAO){
        qnAPostHeartRepositoryJPA.save(qnAPostHeartDAO);
    }
}
