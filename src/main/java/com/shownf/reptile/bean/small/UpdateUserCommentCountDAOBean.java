package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void exec(RequestCommentSaveDTO requestCommentSaveDTO){

        // 댓글 추가된 게시물 유저 아이디 찾기
        Long userId = postRepositoryJPA.findById(requestCommentSaveDTO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 댓글 삭제시 유저 댓글 수 김소
    public void exec(RequestCommentDeleteDTO requestCommentDeleteDTO){

        // 댓글 삭제된 게시물 유저 아이디 찾기
        Long userId = postRepositoryJPA.findById(requestCommentDeleteDTO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 commentCount 감소
        CommentDAO commentDAO = commentRepositoryJPA.findById(requestCommentDeleteDTO.getCommentId()).get();
        if (commentDAO.getReplyCount() == 0)
            userDAO.setCommentCount(userDAO.getCommentCount() - 1);
        else
            userDAO.setCommentCount(userDAO.getCommentCount() - 1 - commentDAO.getReplyCount());

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 대댓글 추가시 유저 댓글 수 증가
    public void exec(RequestReplySaveDTO requestReplySaveDTO){

        // 대댓글 추가된 댓글 찾기
        CommentDAO commentDAO = commentRepositoryJPA.findById(requestReplySaveDTO.getCommentId()).get();

        // 댓글 추가된 게시물 유저 아이디 찾기
        Long userId = postRepositoryJPA.findById(commentDAO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 대댓글 삭제시 유저 댓글 수 감소
    public void exec(RequestReplyDeleteDTO requestReplyDeleteDTO){

        // 대댓글 삭제된 댓글 찾기
        CommentDAO commentDAO = commentRepositoryJPA.findById(requestReplyDeleteDTO.getCommentId()).get();

        // 댓글 추가된 게시물 유저 아이디 찾기
        Long userId = postRepositoryJPA.findById(commentDAO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 commentCount 감소
        userDAO.setCommentCount(userDAO.getCommentCount() - 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }
}
