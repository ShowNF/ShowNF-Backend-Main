package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseCommentsDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateCommentsDTOBean {

    // 댓글 조회시 DTO 생성
    public List<ResponseCommentsDTO> exec(List<CommentDAO> commentDAOs){

        List<ResponseCommentsDTO> responseCommentsDTOS = new ArrayList<>();

        // DTO 객체에 댓글 정보 넘기기
        for (CommentDAO commentDAO: commentDAOs) {
            ResponseCommentsDTO responseCommentsDTO = new ResponseCommentsDTO();

            responseCommentsDTO.setCommentId(commentDAO.getCommentId());
            responseCommentsDTO.setPostId(commentDAO.getPostId());
            responseCommentsDTO.setUserId(commentDAO.getUserId());
            responseCommentsDTO.setContent(commentDAO.getContent());
            responseCommentsDTO.setUploadTime(commentDAO.getUploadTime());
            responseCommentsDTO.setHeartCount(commentDAO.getHeartCount());
            responseCommentsDTO.setReplyCount(commentDAO.getReplyCount());

            responseCommentsDTOS.add(responseCommentsDTO);
        }

        // DTO 객체들 반환
        return responseCommentsDTOS;
    }
}
