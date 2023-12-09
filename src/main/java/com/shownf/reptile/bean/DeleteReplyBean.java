package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteReplyBean {

    GetReplyDAOBean getReplyDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean;
    CheckUserIdCommentDAOBean checkUserIdCommentDAOBean;
    UpdateCommentReplyCountDAOBean updateCommentReplyCountDAOBean;
    UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    SaveReplyDAOBean saveReplyDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteReplyBean(GetReplyDAOBean getReplyDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean, CheckUserIdCommentDAOBean checkUserIdCommentDAOBean, UpdateCommentReplyCountDAOBean updateCommentReplyCountDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, SaveReplyDAOBean saveReplyDAOBean, SaveCommentDAOBean saveCommentDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getReplyDAOBean = getReplyDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.checkCommentIdCommentDAOBean = checkCommentIdCommentDAOBean;
        this.checkUserIdCommentDAOBean = checkUserIdCommentDAOBean;
        this.updateCommentReplyCountDAOBean = updateCommentReplyCountDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 대댓글 삭제
    public Long exec(RequestReplyDeleteDTO requestReplyDeleteDTO, HttpServletRequest request){

        // 대댓글 아이디 찾기
        Long replyId = requestReplyDeleteDTO.getReplyId();

        // 아이디로 삭제할 대댓글 찾기
        ReplyDAO replyDAO = getReplyDAOBean.exec(replyId);
        if (replyDAO == null)
            return 0L;

        // 유저 댓글 토큰으로 확인
        if (!checkUserAccessTokenDAOBean.exec(replyDAO, request))
            return null;

        // 대댓글 deleteCheck 값 true 설정
        replyDAO.setDeleteCheck(true);

        // 대댓글에 해당하는 댓글 확인
        if (!checkCommentIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO))
            return null;

        // 대댓글에 해당하는 유저 아이디 확인
        if (!checkUserIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO))
            return null;

        // 댓글 대댓글 갯수 감소
        CommentDAO commentDAO = updateCommentReplyCountDAOBean.exec(replyId, replyDAO);
        if (commentDAO == null)
            return 0L;

        // 게시물 댓글 감소
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(replyId, commentDAO.getPostId());
        if (postDAO == null)
            return 0L;

        // 대댓글 삭제시 유저 댓글수 감소
        UserDAO userDAO = updateUserCommentCountDAOBean.exec(requestReplyDeleteDTO);
        if (userDAO == null)
            return 0L;

        // 대댓글 저장
        saveReplyDAOBean.exec(replyDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저저장
        saveUserDAOBean.exec(userDAO);

        /*// 대댓글 삭제
        deleteReplyDAOBean.exec(replyDAO);*/

        // replyId 반환
        return replyId;
    }
}
