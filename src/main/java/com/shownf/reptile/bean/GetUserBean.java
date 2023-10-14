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

    public ResponseUserDTO exec(Long handleId){
        UserDAO userDAO = userRepositoryJPA.findById(handleId).get();

        ResponseUserDTO responseUserDTO = new ResponseUserDTO();

        responseUserDTO.setHandleId(userDAO.getHandleId());
        responseUserDTO.setName(userDAO.getUserId());
        responseUserDTO.setImage(userDAO.getImage());
        responseUserDTO.setUserId(userDAO.getUserId());

        return responseUserDTO;
    }
}
