package com.shownf.reptile.bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.shownf.reptile.Model.entity.GoogleUserDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
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

    @Autowired
    public SaveGoogleUserBean(GoogleUserRepositoryJPA googleUserRepositoryJPA, UserRepositoryJPA userRepositoryJPA, CreateUniqueIdBean createUniqueIdBean) {
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
    }

    public void exec(String accessToken, JsonNode userResourceNode){
        String id = userResourceNode.get("id").asText();
        String name = userResourceNode.get("name").asText();
        String picture = userResourceNode.get("picture").asText();

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(12);

        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByGoogleId(id);

        if(googleUserDAO == null){
            googleUserRepositoryJPA.save(new GoogleUserDAO(accessToken, id, localDateTime));
            userRepositoryJPA.save(new UserDAO(createUniqueIdBean.exec(), id, name, picture));
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
