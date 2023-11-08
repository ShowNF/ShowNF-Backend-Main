package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
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
    public void exec(RequestPostSaveDTO requestPostSaveDTO){

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(requestPostSaveDTO.getUserId()).get();

        // 유저 postCount 증가
        userDAO.setPostCount(userDAO.getPostCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }
}
