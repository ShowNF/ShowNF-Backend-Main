package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.DTO.RequestSiteUserUpdateDTO;
import com.shownf.reptile.Model.DTO.ResponseUserDTO;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    GetUserIdBean getUserIdBean;
    GetUserBean getUserBean;
    UpdateUserSiteNameBean updateUserSiteNameBean;
    SaveFollowBean saveFollowBean;
    DeleteFollowBean deleteFollowBean;

    @Autowired
    public UserService(GetUserIdBean getUserIdBean, GetUserBean getUserBean, UpdateUserSiteNameBean updateUserSiteNameBean, SaveFollowBean saveFollowBean, DeleteFollowBean deleteFollowBean) {
        this.getUserIdBean = getUserIdBean;
        this.getUserBean = getUserBean;
        this.updateUserSiteNameBean = updateUserSiteNameBean;
        this.saveFollowBean = saveFollowBean;
        this.deleteFollowBean = deleteFollowBean;
    }

    // 토큰으로 유저 아이디 찾기
    public Long getUserId(String token){
        return getUserIdBean.exec(token);
    }

    // 토큰으로 유저 아이디 찾기
    public ResponseUserDTO getUser(Long userId){
        return getUserBean.exec(userId);
    }

    // 유저 사이트 닉네임 변경
    public Long updateUserSiteName(RequestSiteUserUpdateDTO requestSiteUserUpdateDTO, HttpServletRequest request){
        return updateUserSiteNameBean.exec(requestSiteUserUpdateDTO, request);
    }

    // 팔로우 추가
    public Long followUser(RequestFollowDTO requestFollowDTO){
        return saveFollowBean.exec(requestFollowDTO);
    }

    // 팔로우 취소
    public Long deleteFollowUser(RequestFollowDTO requestFollowDTO){
        return deleteFollowBean.exec(requestFollowDTO);
    }
}
