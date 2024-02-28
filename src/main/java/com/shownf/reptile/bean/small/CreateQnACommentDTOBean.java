package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.ResponseQnACommentGetDTO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateQnACommentDTOBean {

    // QnA 댓글 조회시 DTO 생성
    public ResponseQnACommentGetDTO exec(QnACommentDAO qnACommentDAO){

        ResponseQnACommentGetDTO responseQnACommentGetDTO = new ResponseQnACommentGetDTO();

        responseQnACommentGetDTO.setQnaCommentId(qnACommentDAO.getQnaCommentId());
        responseQnACommentGetDTO.setQnaPostId(qnACommentDAO.getQnaPostId());
        responseQnACommentGetDTO.setUserId(qnACommentDAO.getUserId());
        responseQnACommentGetDTO.setContent(qnACommentDAO.getContent());
        responseQnACommentGetDTO.setImageUrl(qnACommentDAO.getImageUrl());
        responseQnACommentGetDTO.setUploadTime(qnACommentDAO.getUploadTime());
        responseQnACommentGetDTO.setUpdateTime(qnACommentDAO.getUpdateTime());
        responseQnACommentGetDTO.setHeartCount(qnACommentDAO.getHeartCount());
        responseQnACommentGetDTO.setReplyCount(qnACommentDAO.getReplyCount());
        responseQnACommentGetDTO.setSelection(qnACommentDAO.isSelection());

        return responseQnACommentGetDTO;
    }

    // QnA 댓글 전체 조회시 DTO 생성
    public List<ResponseQnACommentGetDTO> exec(List<QnACommentDAO> qnACommentDAOs){

        List<ResponseQnACommentGetDTO> responseQnACommentGetDTOs = new ArrayList<>();

        // DTO 객체에 댓글 정보 넘기기
            for (QnACommentDAO qnACommentDAO : qnACommentDAOs)
                responseQnACommentGetDTOs.add(exec(qnACommentDAO));

        // DTO 객체들 반환
        return responseQnACommentGetDTOs;
    }
}
