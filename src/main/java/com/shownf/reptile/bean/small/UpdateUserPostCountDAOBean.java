package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserPostCountDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserPostCountDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 게시물 추가시 유저 postCount 증가
    public UserDAO exec(RequestPostSaveDTO requestPostSaveDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestPostSaveDTO.getUserId()).orElse(null);
        if (userDAO == null) return null;

        // 유저 postCount 증가
        userDAO.setPostCount(userDAO.getPostCount() + 1);

        return userDAO;
    }

    // 게시물 삭제시 유저 게시물 정보 감소
    public UserDAO exec(RequestPostDeleteDTO requestPostDeleteDTO, PostDAO postDAO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestPostDeleteDTO.getUserId()).orElse(null);
        if (userDAO == null) return null;

        // 유저 postCount 감소
        userDAO.setPostCount(userDAO.getPostCount() - 1);
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() - postDAO.getHeartCount());
        userDAO.setCommentCount(userDAO.getCommentCount() - postDAO.getCommentCount());

        return userDAO;
    }
}
