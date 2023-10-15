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
        Long userId;

        if (kakaoUserDAO != null)
            userId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null)
            userId = googleUserDAO.getGoogleId();
        else userId = null;

        Long handleId = userRepositoryJPA.findByUserId(userId).getHandleId();

        return handleId;

    }
}
