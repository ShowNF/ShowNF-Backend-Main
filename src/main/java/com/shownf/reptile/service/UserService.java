package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestSiteUserUpdateDTO;
import com.shownf.reptile.Model.DTO.ResponseUserDTO;
import com.shownf.reptile.bean.GetUserBean;
import com.shownf.reptile.bean.GetUserIdBean;
import com.shownf.reptile.bean.UpdateUserSiteNameBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    GetUserIdBean getUserIdBean;
    GetUserBean getUserBean;
    UpdateUserSiteNameBean updateUserSiteNameBean;

    @Autowired
    public UserService(GetUserIdBean getUserIdBean, GetUserBean getUserBean, UpdateUserSiteNameBean updateUserSiteNameBean) {
        this.getUserIdBean = getUserIdBean;
        this.getUserBean = getUserBean;
        this.updateUserSiteNameBean = updateUserSiteNameBean;
    }

    // 토큰으로 유저 아이디 찾기
    public Long getUserId(String token){
        return getUserIdBean.exec(token);
    }

    // 토큰으로 유저 아이디 찾기
    public ResponseUserDTO getUser(Long handleId){
        return getUserBean.exec(handleId);
    }

    // 유저 사이트 닉네임 변경
    public Long updateUserSiteName(RequestSiteUserUpdateDTO requestSiteUserUpdateDTO, HttpServletRequest request){
        return updateUserSiteNameBean.exec(requestSiteUserUpdateDTO, request);
    }
}
