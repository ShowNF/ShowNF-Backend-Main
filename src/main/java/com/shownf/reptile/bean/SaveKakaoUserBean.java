package com.shownf.reptile.bean;

import com.shownf.reptile.Model.KakaoProfile;
import com.shownf.reptile.Model.entity.KakaoUserDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.CreateUniqueNicknameBean;
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
    CreateUniqueNicknameBean createUniqueNicknameBean;

    @Autowired
    public SaveKakaoUserBean(KakaoUserRepositoryJPA kakaoUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA, CreateUniqueIdBean createUniqueIdBean, CreateUniqueNicknameBean createUniqueNicknameBean) {
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createUniqueNicknameBean = createUniqueNicknameBean;
    }

    public void exec(String accessToken, KakaoProfile kakaoProfile){

        // 카카오 고유 아이디
        String id = kakaoProfile.getId().toString();

        // 유저 이름
        String name = kakaoProfile.kakao_account.profile.getNickname();

        // 프로필 사진
        String picture = kakaoProfile.kakao_account.profile.getProfile_image_url();

        // 토큰 반환시간
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(12);

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 아이디로 카카오 유저 찾기
        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByKakaoId(id);

        // 아이디가 이미 존재하는지에 따라 로그인 및 회원가입
        if(kakaoUserDAO == null){
            kakaoUserRepositoryJPA.save(new KakaoUserDAO(id, accessToken, localDateTime));
            userRepositoryJPA.save(new UserDAO(createUniqueIdBean.exec(), id, name, picture,
                    "https://reptile-image.s3.ap-northeast-2.amazonaws.com/images/bab06268-da39-4b7d-a138-fa12b0ded4e0-basic-image.png",
                    createUniqueNicknameBean.exec(), uploadTime, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        } else {
            kakaoUserDAO.setAccessToken(accessToken);
            kakaoUserDAO.setExpirationTime(localDateTime);
            kakaoUserRepositoryJPA.save(kakaoUserDAO);

            UserDAO userDAO = userRepositoryJPA.findByOauthId(id);
            userDAO.setName(name);
            userDAO.setImage(picture);
            userRepositoryJPA.save(userDAO);
        }
    }
}
