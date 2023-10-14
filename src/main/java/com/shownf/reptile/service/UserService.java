package com.shownf.reptile.service;

import com.shownf.reptile.bean.GetUserIdBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    GetUserIdBean getUserIdBean;

    @Autowired
    public UserService(GetUserIdBean getUserIdBean) {
        this.getUserIdBean = getUserIdBean;
    }

    // 토큰으로 유저 아이디 찾기
    public Long getUserId(String token){
        return getUserIdBean.exec(token);
    }
}
