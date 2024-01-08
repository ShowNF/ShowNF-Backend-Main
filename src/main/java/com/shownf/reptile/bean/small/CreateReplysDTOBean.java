package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseReplysDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Builder
public class CreateReplysDTOBean {

    // 대댓글 조회시 DTO 생성
    public List<ResponseReplysDTO> exec(List<ReplyDAO> replyDAOs){

        List<ResponseReplysDTO> responseReplysDTOS = new ArrayList<>();

        // DTO 객체 대댓글 정보 넘기기
        for (ReplyDAO replyDAO : replyDAOs){
            ResponseReplysDTO responseReplysDTO = new ResponseReplysDTO();

            responseReplysDTO.setReplyId(replyDAO.getReplyId());
            responseReplysDTO.setCommentId(replyDAO.getCommentId());
            responseReplysDTO.setUserId(replyDAO.getUserId());
            responseReplysDTO.setContent(replyDAO.getContent());
            responseReplysDTO.setUploadTime(replyDAO.getUploadTime());
            responseReplysDTO.setUpdateTime(replyDAO.getUpdateTime());
            responseReplysDTO.setHeartCount(replyDAO.getHeartCount());

            responseReplysDTOS.add(responseReplysDTO);
        }

        // DTO 객체 반환
        return responseReplysDTOS;
    }
}
