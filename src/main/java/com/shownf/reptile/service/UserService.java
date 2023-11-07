package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.DTO.RequestSiteUserUpdateDTO;
import com.shownf.reptile.Model.DTO.ResponseFollowDTO;
import com.shownf.reptile.Model.DTO.ResponseUserDTO;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    GetUserIdBean getUserIdBean;
    GetUserBean getUserBean;
    UpdateUserSiteNameBean updateUserSiteNameBean;
    SaveFollowBean saveFollowBean;
    DeleteFollowBean deleteFollowBean;
    GetFollowersBean getFollowersBean;

    @Autowired
    public UserService(GetUserIdBean getUserIdBean, GetUserBean getUserBean, UpdateUserSiteNameBean updateUserSiteNameBean, SaveFollowBean saveFollowBean, DeleteFollowBean deleteFollowBean, GetFollowersBean getFollowersBean) {
        this.getUserIdBean = getUserIdBean;
        this.getUserBean = getUserBean;
        this.updateUserSiteNameBean = updateUserSiteNameBean;
        this.saveFollowBean = saveFollowBean;
        this.deleteFollowBean = deleteFollowBean;
        this.getFollowersBean = getFollowersBean;
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

    // 유저 팔로워 전체 조회
    public List<ResponseFollowDTO> getFollowersUser(Long userId){
        return getFollowersBean.exec(userId);
    }
}
