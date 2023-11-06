package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSiteUserUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CheckUserAccessTokenDAOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import com.shownf.reptile.bean.small.SaveUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateUserSiteNameBean {

    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public UpdateUserSiteNameBean(GetUserDAOBean getUserDAOBean, SaveUserDAOBean saveUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
    }

    public Long exec(RequestSiteUserUpdateDTO requestSiteUserUpdateDTO, HttpServletRequest request){

        // 핸들 아이디로 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(requestSiteUserUpdateDTO.getHandleId());

        // 토큰 확인
        if(!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return null;

        // 유저 수정 후 저장
        saveUserDAOBean.exec(userDAO, requestSiteUserUpdateDTO);

        return userDAO.getHandleId();
    }
}
