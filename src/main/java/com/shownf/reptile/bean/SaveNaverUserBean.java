package com.shownf.reptile.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.shownf.reptile.Model.entity.NaverUserDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.CreateUniqueNicknameBean;
import com.shownf.reptile.repository.NaverUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveNaverUserBean {

    NaverUserRepositoryJPA naverUserRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;
    CreateUniqueIdBean createUniqueIdBean;
    CreateUniqueNicknameBean createUniqueNicknameBean;

    @Autowired
    public SaveNaverUserBean(NaverUserRepositoryJPA naverUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA, CreateUniqueIdBean createUniqueIdBean, CreateUniqueNicknameBean createUniqueNicknameBean) {
        this.naverUserRepositoryJPA = naverUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createUniqueNicknameBean = createUniqueNicknameBean;
    }

    public void exec(String accessToken, JsonNode userResourceNode){

        // 네이버 고유 아이디
        String id = userResourceNode.get("response").get("id").asText();

        // 이름
        String name = userResourceNode.get("response").get("name").asText();

        // 프로필 사진
        String picture = userResourceNode.get("response").get("profile_image").asText();

        // 이메일
        String email = userResourceNode.get("response").get("email").asText();

        // 토큰 만료시간
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(1);

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 아이디로 네이버 유저 객체 찾기
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByNaverId(id);

        // 아이디가 이미 존재하는지에 따라 로그인 및 회원가입
        if(naverUserDAO == null){
            naverUserRepositoryJPA.save(new NaverUserDAO(id, accessToken, localDateTime));
            userRepositoryJPA.save(new UserDAO(createUniqueIdBean.exec(), id, name, picture,
                    "https://reptile-image.s3.ap-northeast-2.amazonaws.com/images/e60a2776-8d48-458e-a23b-6e0d6ff9e570-IWI_rept2_logo.png",
                    createUniqueNicknameBean.exec(), email, uploadTime, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        } else {
            naverUserDAO.setAccessToken(accessToken);
            naverUserDAO.setExpirationTime(localDateTime);
            naverUserRepositoryJPA.save(naverUserDAO);

            UserDAO userDAO = userRepositoryJPA.findByOauthId(id);
            userDAO.setName(name);
            userDAO.setImage(picture);

            // 임시로 이메일 저장
            userDAO.setEmail(email);
            userRepositoryJPA.save(userDAO);
        }
    }
}
