package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.stereotype.Component;

@Component
public class UpdateQnAPostMetaDAOBean {

    // 메타데이터 조회수 1 증가
    public void exec(QnAPostMeta qnAPostMeta){

        // 게시물 조회수 1 증가
        qnAPostMeta.setViewCount(qnAPostMeta.getViewCount() + 1);
    }

    // QnA 게시물 메타데이터 수정
    public void exec(RequestQnAPostUpdateDTO requestQnAPostUpdateDTO, QnAPostMeta qnAPostMeta){

        qnAPostMeta.setTitle(requestQnAPostUpdateDTO.getTitle());
        qnAPostMeta.setContent(requestQnAPostUpdateDTO.getContent());
        qnAPostMeta.setImageUrl(requestQnAPostUpdateDTO.getImageUrl());
    }

    // QnA 게시물 메타데이터 좋아요 1 증가
    public void exec(QnAPostHeartDAO qnAPostHeartDAO, QnAPostMeta qnAPostMeta){

        // 게시물 조회수 1 증가
        qnAPostMeta.setHeartCount(qnAPostMeta.getHeartCount() + 1);
    }

    // QnA 게시물 메타데이터 좋아요 1 감소
    public void exec(Long check, QnAPostHeartDAO qnAPostHeartDAO, QnAPostMeta qnAPostMeta){

        // 게시물 조회수 1 감소
        qnAPostMeta.setHeartCount(qnAPostMeta.getHeartCount() - 1);
    }
}
