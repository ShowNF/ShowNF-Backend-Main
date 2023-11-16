package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserDiaryCountDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserDiaryCountDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 다이어리 추가시 유저 다이어리 갯수 증가
    public UserDAO exec(PetDAO petDAO){

        // 유저 아이디
        Long userId = petDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 receiveHeart 추가
        userDAO.setDiaryCount(userDAO.getDiaryCount() + 1);

        return userDAO;
    }
}
