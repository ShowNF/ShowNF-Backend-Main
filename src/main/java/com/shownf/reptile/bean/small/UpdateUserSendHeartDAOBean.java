package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSendHeartDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserSendHeartDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 댓글 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(CommentHeartDAO commentHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = commentHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 댓글 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, CommentHeartDAO commentHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = commentHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }
}
