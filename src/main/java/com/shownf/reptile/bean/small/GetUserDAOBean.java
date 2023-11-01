package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestUserSiteNameUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 핸들아이디로 유저 객체 찾기
    public UserDAO exec(Long handleId){
        return userRepositoryJPA.findById(handleId).get();
    }
}
