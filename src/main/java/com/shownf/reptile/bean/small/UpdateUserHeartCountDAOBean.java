package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserHeartCountDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserHeartCountDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 게시물 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestPostHeartSaveDTO requestPostHeartSaveDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestPostHeartSaveDTO.getUserId()).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 댓글 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestCommentHeartSaveDTO.getUserId()).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 대댓글 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestReplyHeartSaveDTO requestReplyHeartSaveDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestReplyHeartSaveDTO.getUserId()).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 댓글 좋아요 삭제시 유저 heartCount 감소
    public void exec(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestCommentHeartDeleteDTO.getUserId()).get();

        // 유저 heartCount 감소
        userDAO.setHeartCount(userDAO.getHeartCount() - 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    // 게시물 좋아요 삭제시 유저 heartCount 감소
    public void exec(RequestPostHeartDeleteDTO requestPostHeartDeleteDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestPostHeartDeleteDTO.getUserId()).get();

        // 유저 heartCount 감소
        userDAO.setHeartCount(userDAO.getHeartCount() - 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }
}
