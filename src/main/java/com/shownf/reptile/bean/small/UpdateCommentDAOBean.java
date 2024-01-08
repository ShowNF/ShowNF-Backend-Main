package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentUpdateDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateCommentDAOBean {

    // Update the comment
    public CommentDAO exec(CommentDAO commentDAO, RequestCommentUpdateDTO requestCommentUpdateDTO){

        // 내용
        commentDAO.setContent(requestCommentUpdateDTO.getContent());

        // 수정시간
        commentDAO.setUpdateTime(LocalDateTime.now());

        return commentDAO;
    }
}
