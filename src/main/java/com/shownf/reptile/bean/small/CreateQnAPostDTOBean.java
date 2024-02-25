package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateQnAPostDTOBean {

    // QnA 게시물 조회시 DTO 생성
    public ResponseQnAPostGetDTO exec(QnAPostDAO qnAPostDAO){
        ResponseQnAPostGetDTO responseQnAPostGetDTO = new ResponseQnAPostGetDTO();

        // DTO 객체에 게시물 정보 넘기기
        responseQnAPostGetDTO.setQnaPostId(qnAPostDAO.getQnaPostId());
        responseQnAPostGetDTO.setUserId(qnAPostDAO.getUserId());
        responseQnAPostGetDTO.setTitle(qnAPostDAO.getTitle());
        responseQnAPostGetDTO.setImageUrl(qnAPostDAO.getImageUrl());
        responseQnAPostGetDTO.setContent(qnAPostDAO.getContent());
        responseQnAPostGetDTO.setUploadTime(qnAPostDAO.getUploadTime());
        responseQnAPostGetDTO.setUpdateTime(qnAPostDAO.getUpdateTime());
        responseQnAPostGetDTO.setHeartCount(qnAPostDAO.getHeartCount());
        responseQnAPostGetDTO.setCommentCount(qnAPostDAO.getCommentCount());
        responseQnAPostGetDTO.setViewCount(qnAPostDAO.getViewCount());

        // DTO 반환
        return responseQnAPostGetDTO;
    }
}
