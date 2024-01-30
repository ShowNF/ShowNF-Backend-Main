package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateUserCommentCountDAOBean {

    GetCommentDAOBean getCommentDAOBean;
    GetPostDAOBean getPostDAOBean;
    GetUserDAOBean getUserDAOBean;

    @Autowired
    public UpdateUserCommentCountDAOBean(GetCommentDAOBean getCommentDAOBean, GetPostDAOBean getPostDAOBean, GetUserDAOBean getUserDAOBean) {
        this.getCommentDAOBean = getCommentDAOBean;
        this.getPostDAOBean = getPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
    }

    // 댓글 추가시 게시글 작성한 유저 댓글 수 증가
    public UserDAO exec(RequestCommentSaveDTO requestCommentSaveDTO){

        // 댓글 추가된 게시물 유저 아이디 찾기
        PostDAO postDAO = getPostDAOBean.exec(requestCommentSaveDTO.getPostId());
        if (postDAO == null) return null;

        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        return userDAO;
    }

    // 댓글 삭제시 게시물 작성한 유저 댓글 수 김소
    public UserDAO exec(RequestCommentDeleteDTO requestCommentDeleteDTO){

        // 댓글 삭제된 게시물 유저 아이디 찾기
        PostDAO postDAO = getPostDAOBean.exec(requestCommentDeleteDTO.getPostId());
        if (postDAO == null) return null;

        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 commentCount 감소
        userDAO.setCommentCount(userDAO.getCommentCount() - 1);

        return userDAO;
    }

    // 대댓글 추가시 유저 댓글 수 증가
    public UserDAO exec(RequestReplySaveDTO requestReplySaveDTO){

        // 대댓글 추가된 댓글 찾기
        CommentDAO commentDAO = getCommentDAOBean.exec(requestReplySaveDTO.getCommentId());
        if (commentDAO == null) return null;

        // 댓글 추가된 게시물 유저 아이디 찾기
        PostDAO postDAO = getPostDAOBean.exec(commentDAO.getPostId());
        if (postDAO == null) return null;

        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        return userDAO;
    }

    // 대댓글 삭제시 유저 댓글 수 감소
    public UserDAO exec(RequestReplyDeleteDTO requestReplyDeleteDTO){

        // 대댓글 추가된 댓글 찾기
        CommentDAO commentDAO = getCommentDAOBean.exec(requestReplyDeleteDTO.getCommentId());
        if (commentDAO == null) return null;

        // 댓글 추가된 게시물 유저 아이디 찾기
        PostDAO postDAO = getPostDAOBean.exec(commentDAO.getPostId());
        if (postDAO == null) return null;

        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 commentCount 감소
        userDAO.setCommentCount(userDAO.getCommentCount() - 1);

        return userDAO;
    }
}
