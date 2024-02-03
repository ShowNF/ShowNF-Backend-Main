package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.GoogleUserDAO;
import com.shownf.reptile.Model.entity.KakaoUserDAO;
import com.shownf.reptile.Model.entity.NaverUserDAO;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import com.shownf.reptile.repository.NaverUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserIdBean {

    KakaoUserRepositoryJPA kakaoUserRepositoryJPA;
    GoogleUserRepositoryJPA googleUserRepositoryJPA;
    NaverUserRepositoryJPA naverUserRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserIdBean(KakaoUserRepositoryJPA kakaoUserRepositoryJPA, GoogleUserRepositoryJPA googleUserRepositoryJPA, NaverUserRepositoryJPA naverUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA) {
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
        this.naverUserRepositoryJPA = naverUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public Long exec(String token){
        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(token);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(token);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByAccessToken(token);
        String oauthId;

        if (kakaoUserDAO != null)
            oauthId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null)
            oauthId = googleUserDAO.getGoogleId();
        else if (naverUserDAO != null)
            oauthId = naverUserDAO.getNaverId();
        else oauthId = null;

        Long userId = userRepositoryJPA.findByOauthId(oauthId).getUserId();

        return userId;

    }
}
