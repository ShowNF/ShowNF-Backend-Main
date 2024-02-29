package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAReplyGetDTO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateQnAReplyDTOBean {

    // QnA 대댓글 조회시 DTO 생성
    public ResponseQnAReplyGetDTO exec(QnAReplyDAO qnAReplyDAO){

        ResponseQnAReplyGetDTO responseQnAReplyGetDTO = new ResponseQnAReplyGetDTO();

        responseQnAReplyGetDTO.setQnaReplyId(qnAReplyDAO.getQnaReplyId());
        responseQnAReplyGetDTO.setQnaCommentId(qnAReplyDAO.getQnaCommentId());
        responseQnAReplyGetDTO.setUserId(qnAReplyDAO.getUserId());
        responseQnAReplyGetDTO.setContent(qnAReplyDAO.getContent());
        responseQnAReplyGetDTO.setUploadTime(qnAReplyDAO.getUploadTime());
        responseQnAReplyGetDTO.setUpdateTime(qnAReplyDAO.getUpdateTime());
        responseQnAReplyGetDTO.setHeartCount(qnAReplyDAO.getHeartCount());

        return responseQnAReplyGetDTO;
    }

    // QnA 대댓글 조회시 DTO 생성
    public List<ResponseQnAReplyGetDTO> exec(List<QnAReplyDAO> qnAReplyDAOS){

        List<ResponseQnAReplyGetDTO> responseQnAReplyGetDTOS = new ArrayList<>();

        // DTO 객체 대댓글 정보 넘기기
        for (QnAReplyDAO qnAReplyDAO : qnAReplyDAOS){
            responseQnAReplyGetDTOS.add(exec(qnAReplyDAO));
        }

        // DTO 객체 반환
        return responseQnAReplyGetDTOS;
    }
}
