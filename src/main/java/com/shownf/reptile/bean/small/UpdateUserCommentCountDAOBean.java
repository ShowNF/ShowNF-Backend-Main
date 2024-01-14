package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateUserCommentCountDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    CommentRepositoryJPA commentRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserCommentCountDAOBean(PostRepositoryJPA postRepositoryJPA, CommentRepositoryJPA commentRepositoryJPA, UserRepositoryJPA userRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.commentRepositoryJPA = commentRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 댓글 추가시 유저 댓글 수 증가
    public UserDAO exec(RequestCommentSaveDTO requestCommentSaveDTO){

        // 댓글 추가된 게시물 유저 아이디 찾기
        PostDAO postDAO = postRepositoryJPA.findById(requestCommentSaveDTO.getPostId()).orElse(null);
        if (postDAO == null) return null;
        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        return userDAO;
    }

    // 댓글 삭제시 유저 댓글 수 김소
    public UserDAO exec(RequestCommentDeleteDTO requestCommentDeleteDTO){

        // 댓글 삭제된 게시물 유저 아이디 찾기
        PostDAO postDAO = postRepositoryJPA.findById(requestCommentDeleteDTO.getPostId()).orElse(null);
        if (postDAO == null) return null;
        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 commentCount 감소
        userDAO.setCommentCount(userDAO.getCommentCount() - 1);

        return userDAO;
    }

    // 대댓글 추가시 유저 댓글 수 증가
    public UserDAO exec(RequestReplySaveDTO requestReplySaveDTO){

        // 대댓글 추가된 댓글 찾기
        CommentDAO commentDAO = commentRepositoryJPA.findById(requestReplySaveDTO.getCommentId()).orElse(null);
        if (commentDAO == null) return null;

        // 댓글 추가된 게시물 유저 아이디 찾기
        PostDAO postDAO = postRepositoryJPA.findById(commentDAO.getPostId()).orElse(null);
        if (postDAO == null) return null;
        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        return userDAO;
    }

    // 대댓글 삭제시 유저 댓글 수 감소
    public UserDAO exec(RequestReplyDeleteDTO requestReplyDeleteDTO){

        // 대댓글 삭제된 댓글 찾기
        CommentDAO commentDAO = commentRepositoryJPA.findById(requestReplyDeleteDTO.getCommentId()).orElse(null);
        if (commentDAO == null) return null;

        // 댓글 추가된 게시물 유저 아이디 찾기
        PostDAO postDAO = postRepositoryJPA.findById(commentDAO.getPostId()).orElse(null);
        if (postDAO == null) return null;
        Long userId = postDAO.getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 commentCount 감소
        userDAO.setCommentCount(userDAO.getCommentCount() - 1);

        return userDAO;
    }
}
