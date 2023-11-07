package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseUserDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserBean {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserBean(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public ResponseUserDTO exec(Long userId){
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        ResponseUserDTO responseUserDTO = new ResponseUserDTO();

        responseUserDTO.setHandleId(userDAO.getUserId());
        responseUserDTO.setName(userDAO.getName());
        responseUserDTO.setImage(userDAO.getImage());
        responseUserDTO.setUserId(userDAO.getOauthId());
        responseUserDTO.setSiteImage(userDAO.getSiteImage());
        responseUserDTO.setSiteName(userDAO.getSiteName());
        responseUserDTO.setFollowerCount(userDAO.getFollowerCount());
        responseUserDTO.setFollowingCount(userDAO.getFollowingCount());

        return responseUserDTO;
    }
}
