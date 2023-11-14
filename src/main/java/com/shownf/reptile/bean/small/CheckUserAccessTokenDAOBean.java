package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.GoogleUserDAO;
import com.shownf.reptile.Model.entity.KakaoUserDAO;
import com.shownf.reptile.Model.entity.NaverUserDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import com.shownf.reptile.repository.NaverUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CheckUserAccessTokenDAOBean {

    KakaoUserRepositoryJPA kakaoUserRepositoryJPA;
    GoogleUserRepositoryJPA googleUserRepositoryJPA;
    NaverUserRepositoryJPA naverUserRepositoryJPA;

    @Autowired
    public CheckUserAccessTokenDAOBean(KakaoUserRepositoryJPA kakaoUserRepositoryJPA, GoogleUserRepositoryJPA googleUserRepositoryJPA, NaverUserRepositoryJPA naverUserRepositoryJPA) {
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
        this.naverUserRepositoryJPA = naverUserRepositoryJPA;
    }

    public boolean exec(UserDAO userDAO, HttpServletRequest request){

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String userToken = httpRequest.getHeader("access-token");

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(userToken);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(userToken);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByAccessToken(userToken);

        String savedUserId;

        if (kakaoUserDAO != null) savedUserId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null) savedUserId = googleUserDAO.getGoogleId();
        else if (naverUserDAO != null) savedUserId = naverUserDAO.getNaverId();
        else savedUserId = null;

        if (userDAO.getOauthId().equals(savedUserId))
            return true;
        return false;
    }
}
