package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 검색어를 기준으로 사용자를 조회하는 메서드
    public List<UserDAO> exec(String search) {
        // UserRepository를 사용하여 검색어를 포함하는 사용자를 조회
        List<UserDAO> userDAOs = userRepositoryJPA.findAllByOrderByFollowingCountDesc();

        // 검색어를 포함하는 사용자만 필터링하여 반환
        return userDAOs.stream()
                .filter(user -> user.getSiteName().contains(search))
                .collect(Collectors.toList());
    }
}
