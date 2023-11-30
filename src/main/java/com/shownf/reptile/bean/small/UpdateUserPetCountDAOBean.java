package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserPetCountDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserPetCountDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 펫 추가시 유저 펫수 증가
    public UserDAO exec(PetDAO petDAO){

        // 유저 아이디
        Long userId = petDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 펫 수 추가
        userDAO.setPetCount(userDAO.getPetCount() + 1);

        return userDAO;
    }

    // 펫 삭제시 유저 펫, 다이어리 수 감소
    public UserDAO exec(Integer diaryCount, PetDAO petDAO){

        // 유저 아이디
        Long userId = petDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).orElse(null);
        if (userDAO == null) return null;

        // 유저 펫 수 추가
        userDAO.setPetCount(userDAO.getPetCount() - 1);

        // 유저 다이어리 수 감소
        userDAO.setDiaryCount(userDAO.getDiaryCount() - diaryCount);

        return userDAO;
    }
}
