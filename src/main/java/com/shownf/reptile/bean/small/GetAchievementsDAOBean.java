package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.Model.Enum.Achievement;
import com.shownf.reptile.Model.entity.AchievementDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.AchievementRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAchievementsDAOBean {

    AchievementRepositoryJPA achievementRepositoryJPA;

    @Autowired
    public GetAchievementsDAOBean(AchievementRepositoryJPA achievementRepositoryJPA) {
        this.achievementRepositoryJPA = achievementRepositoryJPA;
    }

    // 업적 전부 가져오기
    public List<AchievementDAO> exec(){
        List<AchievementDAO> all = achievementRepositoryJPA.findAll();
        System.out.println("all = " + all);
        return all;
    }

    // 시작 안 한 업적 가져오기
    public List<ResponseAchievementDTO> exec(UserDAO userDAO){

        // 업적 가져오기
        List<AchievementDAO> achievementDAOs = exec();

        // 시작 안한 업적
        List<AchievementDAO> achievementDAOList = new ArrayList<>();

        List<ResponseAchievementDTO> responseAchievementDTOs = new ArrayList<>();


        for (AchievementDAO achievementDAO : achievementDAOs){
            Achievement achievement = achievementDAO.getAchievement();
            switch (achievement) {
                case 공감왕:
                    if (userDAO.getSendHeartCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 이구역인싸:
                    if (userDAO.getReceiveHeartCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 멋진작가:
                    if (userDAO.getPostCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 멋진코멘더:
                    achievementDAOList.add(achievementDAO);
                    break;
                case 브리더:
                    if (userDAO.getPetCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 성실왕:
                    if (userDAO.getDiaryCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
            }
        }

        for (AchievementDAO achievementDAO : achievementDAOList){
            ResponseAchievementDTO responseAchievementDTO = new ResponseAchievementDTO();

            responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
            responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
            responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
            responseAchievementDTO.setIcon(achievementDAO.getIcon());
            responseAchievementDTO.setScore(0);

            responseAchievementDTOs.add(responseAchievementDTO);
        }

        return responseAchievementDTOs;
    }
}
