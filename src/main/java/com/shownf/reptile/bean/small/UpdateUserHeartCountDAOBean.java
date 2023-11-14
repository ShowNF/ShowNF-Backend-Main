package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import com.shownf.reptile.repository.ReplyRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserHeartCountDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    CommentRepositoryJPA commentRepositoryJPA;
    ReplyRepositoryJPA replyRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserHeartCountDAOBean(PostRepositoryJPA postRepositoryJPA, CommentRepositoryJPA commentRepositoryJPA, ReplyRepositoryJPA replyRepositoryJPA, UserRepositoryJPA userRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.commentRepositoryJPA = commentRepositoryJPA;
        this.replyRepositoryJPA = replyRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 게시물 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestPostHeartSaveDTO requestPostHeartSaveDTO){

        // 좋아요 누른 게시물 찾기
        Long userId = postRepositoryJPA.findById(requestPostHeartSaveDTO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 게시물 좋아요 삭제시 유저 heartCount 감소
    public void exec(RequestPostHeartDeleteDTO requestPostHeartDeleteDTO){

        // 게시물 삭제할 경우 나중에 추가해야댐

        // 좋아요 누른 게시물 찾기
        Long userId = postRepositoryJPA.findById(requestPostHeartDeleteDTO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 heartCount 감소
        userDAO.setHeartCount(userDAO.getHeartCount() - 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 댓글 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        // 좋아요 누른 게시물 찾기
        Long userId = postRepositoryJPA.findById(commentRepositoryJPA.findById(requestCommentHeartSaveDTO.getCommentId()).get().getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 댓글 좋아요 삭제시 유저 heartCount 감소
    public void exec(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        // 좋아요 누른 게시물 찾기
        Long userId = postRepositoryJPA.findById(commentRepositoryJPA.findById(requestCommentHeartDeleteDTO.getCommentId()).get().getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 heartCount 감소
        userDAO.setHeartCount(userDAO.getHeartCount() - 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 대댓글 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestReplyHeartSaveDTO requestReplyHeartSaveDTO){

        // 좋아요 누른 게시물 찾기
        Long userId = postRepositoryJPA.findById(commentRepositoryJPA.findById(replyRepositoryJPA.findById(requestReplyHeartSaveDTO.getReplyId()).get().getCommentId()).get().getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 대댓글 좋아요 삭제시 유저 heartCount 감소
    public void exec(RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO){

        // 좋아요 누른 게시물 찾기
        Long userId = postRepositoryJPA.findById(commentRepositoryJPA.findById(replyRepositoryJPA.findById(requestReplyHeartDeleteDTO.getReplyId()).get().getCommentId()).get().getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() - 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }
}
