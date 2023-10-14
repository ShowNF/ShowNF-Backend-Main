package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponseUserDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.GetUserBean;
import com.shownf.reptile.bean.GetUserIdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    GetUserIdBean getUserIdBean;
    GetUserBean getUserBean;

    @Autowired
    public UserService(GetUserIdBean getUserIdBean, GetUserBean getUserBean) {
        this.getUserIdBean = getUserIdBean;
        this.getUserBean = getUserBean;
    }

    // 토큰으로 유저 아이디 찾기
    public Long getUserId(String token){
        return getUserIdBean.exec(token);
    }

    // 토큰으로 유저 아이디 찾기
    public ResponseUserDTO getUser(Long handleId){
        return getUserBean.exec(handleId);
    }
}
