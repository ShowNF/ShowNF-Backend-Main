package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.bean.GetAchievementsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {

    GetAchievementsBean getAchievementsBean;

    @Autowired
    public AchievementService(GetAchievementsBean getAchievementsBean) {
        this.getAchievementsBean = getAchievementsBean;
    }

    // 시작 안 한 업적 조회
    public List<ResponseAchievementDTO> getAchievements(Long userId){ return getAchievementsBean.exec(userId); }
}
