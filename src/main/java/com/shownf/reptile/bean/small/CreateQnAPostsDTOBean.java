package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateQnAPostsDTOBean {

    private ResponseQnAPostGetDTO exec(QnAPostDAO qnAPostDAO) {
        ResponseQnAPostGetDTO dto = new ResponseQnAPostGetDTO();
        dto.setQnaPostId(qnAPostDAO.getQnaPostId());
        dto.setUserId(qnAPostDAO.getUserId());
        dto.setTitle(qnAPostDAO.getTitle());
        dto.setImageUrl(qnAPostDAO.getImageUrl());
        dto.setContent(qnAPostDAO.getContent());
        dto.setUploadTime(qnAPostDAO.getUploadTime());
        dto.setUpdateTime(qnAPostDAO.getUpdateTime());
        dto.setHeartCount(qnAPostDAO.getHeartCount());
        dto.setCommentCount(qnAPostDAO.getCommentCount());
        dto.setViewCount(qnAPostDAO.getViewCount());
        return dto;
    }

    // QnA 게시물 DAO 객체 DTO 반환
    public Page<ResponseQnAPostGetDTO> exec(Page<QnAPostDAO> qnAPostDAOs){

        List<QnAPostDAO> content = qnAPostDAOs.getContent();
        List<ResponseQnAPostGetDTO> convertedContent = content.stream()
                .map(this::exec)
                .collect(Collectors.toList());

        return new PageImpl<>(convertedContent, qnAPostDAOs.getPageable(), qnAPostDAOs.getTotalElements());

    }
}
