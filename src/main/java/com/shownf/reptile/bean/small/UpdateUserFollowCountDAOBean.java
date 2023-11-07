package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserFollowCountDAOBean {

    UserRepositoryJPA userRepositoryJPA;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public UpdateUserFollowCountDAOBean(UserRepositoryJPA userRepositoryJPA, SaveUserDAOBean saveUserDAOBean) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 팔로우시 유저 팔로우수와 팔로우 당한 유저 팔로잉수 증가
    public void exec(FollowDAO followDAO){

        // userId 가져오기
        Long userId = followDAO.getUserId();

        // 팔로우한 유저 객체 가져오기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // followUserId 가져오기
        Long followUserId = followDAO.getFollowUserId();

        // 팔로우 당한 유저 객체 가져오기
        UserDAO followedUserDAO = userRepositoryJPA.findById(followUserId).get();

        // 팔로우 팔로잉 1씩 증가
        userDAO.setFollowerCount(userDAO.getFollowerCount() + 1);
        followedUserDAO.setFollowingCount(userDAO.getFollowingCount() + 1);

        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(followedUserDAO);
    }
}
