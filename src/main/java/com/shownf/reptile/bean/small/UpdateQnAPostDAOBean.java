package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
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

    // QnA 게시물 좋아요 개수 추가
    public void exec(QnAPostHeartDAO qnaPostHeartDAO, QnAPostDAO qnaPostDAO){
        // 게시물 좋아요 개수 추가
        qnaPostDAO.setHeartCount(qnaPostDAO.getHeartCount() + 1);
    }

    // QnA 게시물 좋아요 개수 감소
    public void exec(Long check, QnAPostHeartDAO qnaPostHeartDAO, QnAPostDAO qnaPostDAO){
        // 게시물 좋아요 개수 감소
        qnaPostDAO.setHeartCount(qnaPostDAO.getHeartCount() - 1);
    }
}
