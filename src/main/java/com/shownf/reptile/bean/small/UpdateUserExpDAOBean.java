package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.config.UserExpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserExpDAOBean {

    private final UserExpConfig userExpConfig;

    @Autowired
    public UpdateUserExpDAOBean(UserExpConfig userExpConfig) {
        this.userExpConfig = userExpConfig;
    }

    // Update the user exp
    public UserDAO exec(PostHeartDAO postHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }
}
