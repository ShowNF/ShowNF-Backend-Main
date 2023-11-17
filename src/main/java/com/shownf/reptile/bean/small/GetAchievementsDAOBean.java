package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.Model.Enum.Achievement;
import com.shownf.reptile.Model.Enum.Grade;
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

    // 진행중인 업적 가져오기
    public List<ResponseAchievementDTO> exec(Long check, UserDAO userDAO){

        // 업적 가져오기
        List<AchievementDAO> achievementDAOs = exec();

        List<ResponseAchievementDTO> responseAchievementDTOs = new ArrayList<>();

        for (AchievementDAO achievementDAO : achievementDAOs){

            ResponseAchievementDTO responseAchievementDTO = new ResponseAchievementDTO();

            String grade;
            Achievement achievement = achievementDAO.getAchievement();

            switch (achievement) {
                case 공감왕:
                    Integer sendHeartCount = userDAO.getSendHeartCount();
                    if (sendHeartCount<=0) break;
                    if (sendHeartCount >= 3000)
                        grade = Grade.DIAMOND.name();
                    else if (sendHeartCount >= 1500)
                        grade = Grade.PLATINUM.name();
                    else if (sendHeartCount >= 750)
                        grade = Grade.GOLD.name();
                    else if (sendHeartCount >= 100)
                        grade = Grade.SILVER.name();
                    else if (sendHeartCount >= 10)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setScore(sendHeartCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 이구역인싸:
                    Integer receiveHeartCount = userDAO.getReceiveHeartCount();
                    if (receiveHeartCount<=0) break;
                    if (receiveHeartCount >= 3000)
                        grade = Grade.DIAMOND.name();
                    else if (receiveHeartCount >= 1500)
                        grade = Grade.PLATINUM.name();
                    else if (receiveHeartCount >= 750)
                        grade = Grade.GOLD.name();
                    else if (receiveHeartCount >= 100)
                        grade = Grade.SILVER.name();
                    else if (receiveHeartCount >= 10)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setScore(receiveHeartCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 멋진작가:
                    Integer postCount = userDAO.getPostCount();
                    if (postCount<=0) break;
                    if (postCount >= 300)
                        grade = Grade.DIAMOND.name();
                    else if (postCount >= 150)
                        grade = Grade.PLATINUM.name();
                    else if (postCount >= 70)
                        grade = Grade.GOLD.name();
                    else if (postCount >= 25)
                        grade = Grade.SILVER.name();
                    else if (postCount >= 5)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setScore(postCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 멋진코멘더:
                    break;
                case 브리더:
                    Integer petCount = userDAO.getPetCount();
                    if (petCount<=0) break;
                    if (petCount >= 100)
                        grade = Grade.DIAMOND.name();
                    else if (petCount >= 50)
                        grade = Grade.PLATINUM.name();
                    else if (petCount >= 20)
                        grade = Grade.GOLD.name();
                    else if (petCount >= 10)
                        grade = Grade.SILVER.name();
                    else if (petCount >= 3)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setScore(petCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 성실왕:
                    Integer diaryCount = userDAO.getDiaryCount();
                    if (diaryCount<=0) break;
                    if (diaryCount >= 3000)
                        grade = Grade.DIAMOND.name();
                    else if (diaryCount >= 1500)
                        grade = Grade.PLATINUM.name();
                    else if (diaryCount >= 750)
                        grade = Grade.GOLD.name();
                    else if (diaryCount >= 100)
                        grade = Grade.SILVER.name();
                    else if (diaryCount >= 10)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setScore(diaryCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
            }
        }

        return responseAchievementDTOs;
    }
}
