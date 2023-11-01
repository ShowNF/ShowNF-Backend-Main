package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestUserSiteNameUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public SaveUserDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 유저 저장
    public void exec(UserDAO userDAO){
        userRepositoryJPA.save(userDAO);
    }

    // 유저 사이트 이름 수정
    public void exec(UserDAO userDAO, RequestUserSiteNameUpdateDTO requestUserSiteNameUpdateDTO){
        userDAO.setSiteName(requestUserSiteNameUpdateDTO.getSiteName());
        exec(userDAO);
    }

}
