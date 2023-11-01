package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestUserSiteNameUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import com.shownf.reptile.bean.small.SaveUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSiteNameBean {

    GetUserDAOBean getUserDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public UpdateUserSiteNameBean(GetUserDAOBean getUserDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public Long exec(RequestUserSiteNameUpdateDTO requestUserSiteNameUpdateDTO){

        // 핸들 아이디로 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(requestUserSiteNameUpdateDTO.getHandleId());

        // 유저 수정 후 저장
        saveUserDAOBean.exec(userDAO, requestUserSiteNameUpdateDTO);

        return userDAO.getHandleId();
    }
}
