package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.Model.Enum.Achievement;
import com.shownf.reptile.Model.Enum.Grade;
import com.shownf.reptile.Model.entity.AchievementDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.AchievementRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAchievementsDAOBean {

    AchievementRepositoryJPA achievementRepositoryJPA;
    GetPetsDAOBean getPetsDAOBean;

    @Autowired
    public GetAchievementsDAOBean(AchievementRepositoryJPA achievementRepositoryJPA, GetPetsDAOBean getPetsDAOBean) {
        this.achievementRepositoryJPA = achievementRepositoryJPA;
        this.getPetsDAOBean = getPetsDAOBean;
    }

    // 업적 전부 가져오기
    public List<AchievementDAO> exec(){
        return achievementRepositoryJPA.findAll();
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
                case 인플루언서:
                    if (userDAO.getFollowerCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 전문브리더:
                    List<PetDAO> petDAOs = getPetsDAOBean.exec(userDAO.getUserId());

                    int count = 0;

                    for (PetDAO petDAO : petDAOs){
                        if (petDAO.getLevel().ordinal() >= 6)
                            count++;
                    }
                    if (count == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 지식인:
                    if (userDAO.getSelectionCount() == 0) achievementDAOList.add(achievementDAO);
                    break;
                case 펫전문가:
                    if (userDAO.getExp() == 0) achievementDAOList.add(achievementDAO);
                    break;
            }
        }

        for (AchievementDAO achievementDAO : achievementDAOList){
            ResponseAchievementDTO responseAchievementDTO = new ResponseAchievementDTO();

            responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
            responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
            responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
            responseAchievementDTO.setIcon(achievementDAO.getIcon());
            responseAchievementDTO.setStyle(achievementDAO.getStyle());
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
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
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
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
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
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
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
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
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
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
                    responseAchievementDTO.setScore(diaryCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 인플루언서:
                    Integer followerCount = userDAO.getFollowerCount();
                    if (followerCount<=0) break;
                    if (followerCount >= 500)
                        grade = Grade.DIAMOND.name();
                    else if (followerCount >= 300)
                        grade = Grade.PLATINUM.name();
                    else if (followerCount >= 100)
                        grade = Grade.GOLD.name();
                    else if (followerCount >= 50)
                        grade = Grade.SILVER.name();
                    else if (followerCount >= 10)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
                    responseAchievementDTO.setScore(followerCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 전문브리더:
                    List<PetDAO> petDAOs = getPetsDAOBean.exec(userDAO.getUserId());

                    int count = 0;

                    for (PetDAO petDAO : petDAOs){
                        if (petDAO.getLevel().ordinal() >= 6)
                            count++;
                    }

                    if (count<=0) break;
                    if (count >= 10)
                        grade = Grade.DIAMOND.name();
                    else if (count >= 8)
                        grade = Grade.PLATINUM.name();
                    else if (count >= 6)
                        grade = Grade.GOLD.name();
                    else if (count >= 4)
                        grade = Grade.SILVER.name();
                    else if (count >= 2)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
                    responseAchievementDTO.setScore(count);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 지식인:
                    Integer selectionCount = userDAO.getSelectionCount();
                    if (selectionCount<=0) break;
                    if (selectionCount >= 100)
                        grade = Grade.DIAMOND.name();
                    else if (selectionCount >= 50)
                        grade = Grade.PLATINUM.name();
                    else if (selectionCount >= 20)
                        grade = Grade.GOLD.name();
                    else if (selectionCount >= 7)
                        grade = Grade.SILVER.name();
                    else if (selectionCount >= 3)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
                    responseAchievementDTO.setScore(selectionCount);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
                case 펫전문가:
                    Integer exp = userDAO.getExp();
                    if (exp<=0) break;
                    if (exp >= 500)
                        grade = Grade.DIAMOND.name();
                    else if (exp >= 300)
                        grade = Grade.PLATINUM.name();
                    else if (exp >= 100)
                        grade = Grade.GOLD.name();
                    else if (exp >= 50)
                        grade = Grade.SILVER.name();
                    else if (exp >= 10)
                        grade = Grade.BRONZE.name();
                    else
                        grade = Grade.UNRANKED.name();
                    responseAchievementDTO.setAchievement(achievementDAO.getAchievement());
                    responseAchievementDTO.setAchievementExplain(achievementDAO.getAchievementExplain());
                    responseAchievementDTO.setAchievementName(achievementDAO.getAchievementName());
                    responseAchievementDTO.setIcon(achievementDAO.getIcon());
                    responseAchievementDTO.setStyle(achievementDAO.getStyle());
                    responseAchievementDTO.setScore(exp);
                    responseAchievementDTO.setGrade(grade);

                    responseAchievementDTOs.add(responseAchievementDTO);
                    break;
            }
        }

        return responseAchievementDTOs;
    }
}
