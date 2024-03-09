package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetUserDAOBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserDAOBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 핸들아이디로 유저 객체 찾기
    public UserDAO exec(Long userId){
        return userRepositoryJPA.findById(userId).orElse(null);
    }

    // 팔로워순으로 유저 5명 찾기
    public List<UserDAO> exec(){
        return userRepositoryJPA.findTop5ByOrderByFollowingCountDesc();
    }
}
