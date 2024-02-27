package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.repository.qna.QnAPostHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteQnAPostHeartDAOBean {

    QnAPostHeartRepositoryJPA qnAPostHeartRepositoryJPA;

    @Autowired
    public DeleteQnAPostHeartDAOBean(QnAPostHeartRepositoryJPA qnAPostHeartRepositoryJPA) {
        this.qnAPostHeartRepositoryJPA = qnAPostHeartRepositoryJPA;
    }

    // QnA 게시물 좋아요 삭제
    public void exec(QnAPostHeartDAO qnAPostHeartDAO){
        qnAPostHeartRepositoryJPA.delete(qnAPostHeartDAO);
    }


}
