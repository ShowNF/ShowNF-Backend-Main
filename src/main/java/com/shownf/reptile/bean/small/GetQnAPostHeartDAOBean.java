package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.repository.qna.QnAPostHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostHeartDAOBean {

    QnAPostHeartRepositoryJPA qnAPostHeartRepositoryJPA;

    @Autowired
    public GetQnAPostHeartDAOBean(QnAPostHeartRepositoryJPA qnAPostHeartRepositoryJPA) {
        this.qnAPostHeartRepositoryJPA = qnAPostHeartRepositoryJPA;
    }

    // QnA 게시물 좋아요 중복 배제를 위한 객체 찾기
    public QnAPostHeartDAO exec(RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){
        return qnAPostHeartRepositoryJPA.findByUserIdAndQnaPostId(requestQnAPostHeartSaveDTO.getUserId(), requestQnAPostHeartSaveDTO.getQnaPostId());
    }

    // QnA 게시물 좋아요 중복 배제를 위한 객체 찾기
    public QnAPostHeartDAO exec(RequestQnAPostHeartDeleteDTO requestQnAPostHeartDeleteDTO){
        return qnAPostHeartRepositoryJPA.findByUserIdAndQnaPostId(requestQnAPostHeartDeleteDTO.getUserId(), requestQnAPostHeartDeleteDTO.getQnaPostId());
    }
}
