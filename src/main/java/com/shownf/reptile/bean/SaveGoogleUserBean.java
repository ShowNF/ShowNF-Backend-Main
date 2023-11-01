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

        // 토큰 만료시간
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(12);

        // 아이디로 구글 유저 객체 찾기
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByGoogleId(id);

        // 아이디가 이미 존재하는지에 따라 로그인 및 회원가입
        if(googleUserDAO == null){
            googleUserRepositoryJPA.save(new GoogleUserDAO(id, accessToken, localDateTime));
            userRepositoryJPA.save(new UserDAO(createUniqueIdBean.exec(), id, name, picture, "default",createUniqueNicknameBean.exec()));
        } else {
            googleUserDAO.setAccessToken(accessToken);
            googleUserDAO.setExpirationTime(localDateTime);
            googleUserRepositoryJPA.save(googleUserDAO);

            UserDAO userDAO = userRepositoryJPA.findByUserId(id);
            userDAO.setName(name);
            userDAO.setImage(picture);
            userRepositoryJPA.save(userDAO);
        }
    }
}
