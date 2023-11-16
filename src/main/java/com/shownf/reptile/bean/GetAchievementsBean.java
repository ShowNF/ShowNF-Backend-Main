package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.GetAchievementsDAOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAchievementsBean {

    GetUserDAOBean getUserDAOBean;
    GetAchievementsDAOBean getAchievementsDAOBean;

    @Autowired
    public GetAchievementsBean(GetUserDAOBean getUserDAOBean, GetAchievementsDAOBean getAchievementsDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
        this.getAchievementsDAOBean = getAchievementsDAOBean;
    }

    // 시작 안 한 업적 조회
    public List<ResponseAchievementDTO> exec(Long userId){

        // 유저 아이디로 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저의 시작 안한 업적 가져오기
        return getAchievementsDAOBean.exec(userDAO);
    }
}
