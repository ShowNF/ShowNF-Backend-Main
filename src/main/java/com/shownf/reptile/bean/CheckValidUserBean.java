package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CheckUserAccessTokenDAOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CheckValidUserBean {

    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;

    @Autowired
    public CheckValidUserBean(GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
    }

    // token valid check
    public boolean exec(Long userId, HttpServletRequest request){

        // find user
        UserDAO userDAO = getUserDAOBean.exec(userId);

        // token check
        return checkUserAccessTokenDAOBean.exec(userDAO.getOauthId(), request);
    }
}
