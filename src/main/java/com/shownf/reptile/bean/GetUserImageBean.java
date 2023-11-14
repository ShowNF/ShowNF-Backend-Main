package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserImageBean {

    GetUserDAOBean getUserDAOBean;

    @Autowired
    public GetUserImageBean(GetUserDAOBean getUserDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
    }

    public String exec(Long userId){

        // 유저 아이디로 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);

        // 유저 이미지 찾고 반환
        return userDAO.getSiteImage();
    }
}
