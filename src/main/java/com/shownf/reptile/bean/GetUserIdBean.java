package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.GoogleUserDAO;
import com.shownf.reptile.Model.entity.KakaoUserDAO;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserIdBean {

    KakaoUserRepositoryJPA kakaoUserRepositoryJPA;
    GoogleUserRepositoryJPA googleUserRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public GetUserIdBean(KakaoUserRepositoryJPA kakaoUserRepositoryJPA, GoogleUserRepositoryJPA googleUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA) {
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public Long exec(String token){
        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(token);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(token);
        String oauthId;

        if (kakaoUserDAO != null)
            oauthId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null)
            oauthId = googleUserDAO.getGoogleId();
        else oauthId = null;

        Long userId = userRepositoryJPA.findByUserId(oauthId).getUserId();

        return userId;

    }
}
