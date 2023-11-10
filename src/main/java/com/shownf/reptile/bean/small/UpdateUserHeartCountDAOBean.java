package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
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

    // 좋아요 추가시 유저 heartCount 증가
    public void exec(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestCommentHeartSaveDTO.getUserId()).get();

        // 유저 heartCount 증가
        userDAO.setHeartCount(userDAO.getHeartCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }
}
