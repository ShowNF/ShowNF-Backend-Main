package com.shownf.reptile.bean;

import com.shownf.reptile.Model.KakaoProfile;
import com.shownf.reptile.Model.entity.KakaoUserDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveKakaoUserBean {

    KakaoUserRepositoryJPA kakaoUserRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;
    CreateUniqueIdBean createUniqueIdBean;

    @Autowired
    public SaveKakaoUserBean(KakaoUserRepositoryJPA kakaoUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA, CreateUniqueIdBean createUniqueIdBean) {
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
    }

    public void exec(String accessToken, KakaoProfile kakaoProfile){
        String id = kakaoProfile.getId().toString();
        String name = kakaoProfile.kakao_account.profile.getNickname();
        String picture = kakaoProfile.kakao_account.profile.getProfile_image_url();

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(12);

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByKakaoId(id);

        if(kakaoUserDAO == null){
            kakaoUserRepositoryJPA.save(new KakaoUserDAO(accessToken, id, localDateTime));
            userRepositoryJPA.save(new UserDAO(createUniqueIdBean.exec(), id, name, picture));
        } else {
            kakaoUserDAO.setAccessToken(accessToken);
            kakaoUserDAO.setExpirationTime(localDateTime);

            kakaoUserRepositoryJPA.save(kakaoUserDAO);
            UserDAO userDAO = userRepositoryJPA.findByUserId(id);
            userDAO.setName(name);
            userDAO.setImage(picture);
            userRepositoryJPA.save(userDAO);
        }
    }
}
