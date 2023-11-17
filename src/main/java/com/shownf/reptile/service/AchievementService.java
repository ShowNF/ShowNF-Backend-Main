package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.bean.GetAchievementsBean;
import com.shownf.reptile.bean.GetStartedAchievementsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {

    GetAchievementsBean getAchievementsBean;
    GetStartedAchievementsBean getStartedAchievementsBean;
    @Autowired
    public AchievementService(GetAchievementsBean getAchievementsBean, GetStartedAchievementsBean getStartedAchievementsBean) {
        this.getAchievementsBean = getAchievementsBean;
        this.getStartedAchievementsBean = getStartedAchievementsBean;
    }

    // 시작 안 한 업적 조회
    public List<ResponseAchievementDTO> getAchievements(Long userId){ return getAchievementsBean.exec(userId); }

    // 시작한 업적 조회
    public List<ResponseAchievementDTO> getStartedAchievements(Long userId){ return getStartedAchievementsBean.exec(userId); }
}
