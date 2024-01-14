package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteCommentBean {

    GetCommentDAOBean getCommentDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    CheckPostIdPostDAOBean checkPostIdPostDAOBean;
    CheckUserIdPostDAOBean checkUserIdPostDAOBean;
    UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteCommentBean(GetCommentDAOBean getCommentDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, CheckPostIdPostDAOBean checkPostIdPostDAOBean, CheckUserIdPostDAOBean checkUserIdPostDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveCommentDAOBean saveCommentDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getCommentDAOBean = getCommentDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.checkPostIdPostDAOBean = checkPostIdPostDAOBean;
        this.checkUserIdPostDAOBean = checkUserIdPostDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 댓글 삭제
    public Long exec(RequestCommentDeleteDTO requestCommentDeleteDTO, HttpServletRequest request){

        // 댓글 아이디 찾기
        Long commentId = requestCommentDeleteDTO.getCommentId();

        // 아이디로 삭제할 댓글 찾기
        CommentDAO commentDAO =  getCommentDAOBean.exec(commentId);
        if (commentDAO == null) return 0L;

        // 유저 댓글 토큰으로 확인
        if (!checkUserAccessTokenDAOBean.exec(commentDAO, request))
            return null;

        // 댓글 deleteCheck 값 true 변경
        commentDAO.setDeleteCheck(true);

        // 댓글에 해당하는 게시물 확인
        if (!checkPostIdPostDAOBean.exec(commentDAO, requestCommentDeleteDTO))
            return 0L;

        // 댓글에 해당하는 아이디 확인
        if (!checkUserIdPostDAOBean.exec(commentDAO, requestCommentDeleteDTO))
            return 0L;

        // 게시물 댓글 갯수 감소
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(commentId, commentDAO);
        if (postDAO == null) return 0L;

        // 댓글 삭제시 유저 댓글수 감소
        UserDAO userDAO = updateUserCommentCountDAOBean.exec(requestCommentDeleteDTO);
        if (userDAO == null) return 0L;

        // 댓글 삭제한 유저
        UserDAO userDAO1 = getUserDAOBean.exec(requestCommentDeleteDTO.getUserId());

        // 경험치 삭제
        userDAO1 = updateUserExpDAOBean.exec(requestCommentDeleteDTO, userDAO1);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);

        // commentId 반환
        return  commentId;
    }
}
