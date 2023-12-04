package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCheckCommentDAOBean {

    // 댓글 삭제 여부 확인
    public List<CommentDAO> exec(List<CommentDAO> commentDAOs){

        List<CommentDAO> newCommentDAOs = new ArrayList<>();

        // 삭제 확인
        for (CommentDAO commentDAO : commentDAOs){
            if (commentDAO.isDeleteCheck()) {
                commentDAO.setContent("삭제된 댓글입니다");
                commentDAO.setUserId(0L);
                commentDAO.setHeartCount(0);
                commentDAO.setReplyCount(0);
            }
            newCommentDAOs.add(commentDAO);
        }

        return newCommentDAOs;
    }
}
