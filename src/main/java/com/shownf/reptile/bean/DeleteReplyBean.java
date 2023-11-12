package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteReplyBean {

    GetReplyDAOBean getReplyDAOBean;
    CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean;
    CheckUserIdCommentDAOBean checkUserIdCommentDAOBean;
    DeleteReplyDAOBean deleteReplyDAOBean;
    UpdateCommentReplyCountDAOBean updateCommentReplyCountDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;
    SavePostDAOBean savePostDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;

    @Autowired
    public DeleteReplyBean(GetReplyDAOBean getReplyDAOBean, CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean, CheckUserIdCommentDAOBean checkUserIdCommentDAOBean, DeleteReplyDAOBean deleteReplyDAOBean, UpdateCommentReplyCountDAOBean updateCommentReplyCountDAOBean, SaveCommentDAOBean saveCommentDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, SavePostDAOBean savePostDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean) {
        this.getReplyDAOBean = getReplyDAOBean;
        this.checkCommentIdCommentDAOBean = checkCommentIdCommentDAOBean;
        this.checkUserIdCommentDAOBean = checkUserIdCommentDAOBean;
        this.deleteReplyDAOBean = deleteReplyDAOBean;
        this.updateCommentReplyCountDAOBean = updateCommentReplyCountDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
    }

    // 대댓글 삭제
    public Long exec(RequestReplyDeleteDTO requestReplyDeleteDTO){

        // 대댓글 아이디 찾기
        Long replyId = requestReplyDeleteDTO.getReplyId();

        // 아이디로 삭제할 대댓글 찾기
        ReplyDAO replyDAO = getReplyDAOBean.exec(replyId);

        // 대댓글에 해당하는 댓글 확인
        if (!checkCommentIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO))
            return null;

        // 대댓글에 해당하는 유저 아이디 확인
        if (!checkUserIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO))
            return null;

        // 대댓글 삭제
        deleteReplyDAOBean.exec(replyDAO);

        // 댓글 대댓글 갯수 감소
        CommentDAO commentDAO = updateCommentReplyCountDAOBean.exec(replyId, replyDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 댓글 감소
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(replyId, commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 대댓글 삭제시 유저 댓글수 감소
        updateUserCommentCountDAOBean.exec(requestReplyDeleteDTO);

        // replyId 반환
        return replyId;
    }
}
