package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.stereotype.Component;

@Component
public class UpdateQnAPostDAOBean {

    // QnA 게시물 수정
    public void exec(RequestQnAPostUpdateDTO requestQnAPostUpdateDTO, QnAPostDAO qnAPostDAO){

        qnAPostDAO.setTitle(requestQnAPostUpdateDTO.getTitle());
        qnAPostDAO.setContent(requestQnAPostUpdateDTO.getContent());
        qnAPostDAO.setImageUrl(requestQnAPostUpdateDTO.getImageUrl());
    }

    // QnA 게시물 찾기에 대한 조회수 1 증가
    public void exec(QnAPostDAO qnAPostDAO){
        // 게시물 조회수 1 증가
        qnAPostDAO.setViewCount(qnAPostDAO.getViewCount() + 1);
    }
}
