package com.shownf.reptile.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.shownf.reptile.Model.entity.GoogleUserDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.CreateUniqueNicknameBean;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveGoogleUserBean {

    GoogleUserRepositoryJPA googleUserRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;
    CreateUniqueIdBean createUniqueIdBean;
    CreateUniqueNicknameBean createUniqueNicknameBean;

    @Autowired
    public SaveGoogleUserBean(GoogleUserRepositoryJPA googleUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA, CreateUniqueIdBean createUniqueIdBean, CreateUniqueNicknameBean createUniqueNicknameBean) {
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createUniqueNicknameBean = createUniqueNicknameBean;
    }

    public void exec(String accessToken, JsonNode userResourceNode){

        // 구글 고유 아이디
        String id = userResourceNode.get("id").asText();

        // 이름
        String name = userResourceNode.get("name").asText();

        // 프로필 사진
        String picture = userResourceNode.get("picture").asText();

        // 이메일
        String email = userResourceNode.get("email").asText();

        // 토큰 만료시간
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(12);

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 아이디로 구글 유저 객체 찾기
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByGoogleId(id);

        // 아이디가 이미 존재하는지에 따라 로그인 및 회원가입
        if(googleUserDAO == null){
            googleUserRepositoryJPA.save(new GoogleUserDAO(id, accessToken, localDateTime));
            userRepositoryJPA.save(new UserDAO(createUniqueIdBean.exec(), id, name, picture,
                    "https://reptile-image.s3.ap-northeast-2.amazonaws.com/images/e60a2776-8d48-458e-a23b-6e0d6ff9e570-IWI_rept2_logo.png",
                    createUniqueNicknameBean.exec(), email, uploadTime, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        } else {
            googleUserDAO.setAccessToken(accessToken);
            googleUserDAO.setExpirationTime(localDateTime);
            googleUserRepositoryJPA.save(googleUserDAO);

            UserDAO userDAO = userRepositoryJPA.findByOauthId(id);
            userDAO.setName(name);
            userDAO.setImage(picture);

            // 임시로 이메일 저장
            userDAO.setEmail(email);
            userRepositoryJPA.save(userDAO);
        }
    }
}
