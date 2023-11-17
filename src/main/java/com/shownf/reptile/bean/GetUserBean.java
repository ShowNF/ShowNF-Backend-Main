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

        responseUserDTO.setUserId(userDAO.getUserId());
        responseUserDTO.setName(userDAO.getName());
        responseUserDTO.setImage(userDAO.getImage());
        responseUserDTO.setOauthId(userDAO.getOauthId());
        responseUserDTO.setSiteImage(userDAO.getSiteImage());
        responseUserDTO.setSiteName(userDAO.getSiteName());
        responseUserDTO.setFollowerCount(userDAO.getFollowerCount());
        responseUserDTO.setFollowingCount(userDAO.getFollowingCount());
        responseUserDTO.setPostCount(userDAO.getPostCount());
        responseUserDTO.setHeartCount(userDAO.getHeartCount());
        responseUserDTO.setCommentCount(userDAO.getCommentCount());
        responseUserDTO.setPetCount(userDAO.getPetCount());
        responseUserDTO.setDiaryCount(userDAO.getDiaryCount());
        responseUserDTO.setSendCommentCount(userDAO.getSendCommentCount());
        responseUserDTO.setSendHeartCount(userDAO.getSendHeartCount());
        responseUserDTO.setReceiveHeartCount(userDAO.getReceiveHeartCount());

        return responseUserDTO;
    }
}
