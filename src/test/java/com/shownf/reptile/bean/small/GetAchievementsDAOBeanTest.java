package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.Model.Enum.Achievement;
import com.shownf.reptile.Model.Enum.Grade;
import com.shownf.reptile.Model.entity.AchievementDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.AchievementRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetAchievementsDAOBeanTest {

    @Mock
    private AchievementRepositoryJPA achievementRepositoryJPA;

    @InjectMocks
    private GetAchievementsDAOBean getAchievementsDAOBean;

    @Test
    public void testGetAllAchievements() {
        MockitoAnnotations.initMocks(this);

        // 모의 데이터 설정
        AchievementDAO achievement1 = new AchievementDAO();
        achievement1.setAchievementName("Achievement 1");
        achievement1.setAchievementExplain("Explanation 1");

        AchievementDAO achievement2 = new AchievementDAO();
        achievement2.setAchievementName("Achievement 2");
        achievement2.setAchievementExplain("Explanation 2");

        List<AchievementDAO> mockAchievements = Arrays.asList(achievement1, achievement2);

        // 모의화한 repository가 findAll() 메서드를 호출하면 모의 데이터를 반환하도록 설정
        when(achievementRepositoryJPA.findAll()).thenReturn(mockAchievements);

        // 테스트
        List<AchievementDAO> result = getAchievementsDAOBean.exec();

        // 기대값과 실제값 비교
        assertEquals(mockAchievements.size(), result.size());
        assertEquals(mockAchievements.get(0), result.get(0));
        assertEquals(mockAchievements.get(1), result.get(1));
    }

    @Test
    public void testGetUnstartedAchievements() {
        MockitoAnnotations.initMocks(this);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);
        userDAO.setSendHeartCount(0);
        userDAO.setReceiveHeartCount(0);
        userDAO.setPostCount(0);
        userDAO.setPetCount(0);
        userDAO.setDiaryCount(0);
        userDAO.setFollowerCount(0);

        // Mocking the achievements
        List<AchievementDAO> achievementDAOs = new ArrayList<>();

        AchievementDAO achievementDAO = new AchievementDAO();
        achievementDAO.setAchievement(Achievement.공감왕);
        achievementDAO.setAchievementName("공감왕");
        achievementDAO.setAchievementExplain("Explanation 1");
        achievementDAO.setIcon("Icon");
        achievementDAO.setStyle("Style");
        achievementDAOs.add(achievementDAO);

        AchievementDAO achievementDAO1 = new AchievementDAO();
        achievementDAO1.setAchievement(Achievement.공감왕);
        achievementDAO1.setAchievementName("공감왕");
        achievementDAO1.setAchievementExplain("Explanation 1");
        achievementDAO1.setIcon("Icon");
        achievementDAO1.setStyle("Style");
        achievementDAOs.add(achievementDAO1);

        AchievementDAO achievementDAO2 = new AchievementDAO();
        achievementDAO2.setAchievement(Achievement.공감왕);
        achievementDAO2.setAchievementName("공감왕");
        achievementDAO2.setAchievementExplain("Explanation 1");
        achievementDAO2.setIcon("Icon");
        achievementDAO2.setStyle("Style");
        achievementDAOs.add(achievementDAO2);


        // Add more achievements as needed

        when(achievementRepositoryJPA.findAll()).thenReturn(achievementDAOs);

        // Test
        List<ResponseAchievementDTO> result = getAchievementsDAOBean.exec(userDAO);

        // Verify that the correct achievements are returned
        assertEquals(3, result.size());  // Adjust the expected count based on the number of unstarted achievements

        // Verify that the correct achievements are included in the result
        for (ResponseAchievementDTO responseAchievementDTO : result) {
            // Adjust the conditions based on the unstarted achievements
            switch (responseAchievementDTO.getAchievement()) {
                case 공감왕:
                    assertEquals(0, userDAO.getSendHeartCount());
                    break;
                case 이구역인싸:
                    assertEquals(0, userDAO.getReceiveHeartCount());
                    break;
                case 멋진작가:
                    assertEquals(0, userDAO.getPostCount());
                    break;
            }
        }
    }

    @Test
    public void testGetInProgressAchievements() {
        MockitoAnnotations.initMocks(this);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);
        userDAO.setSendHeartCount(100); // Adjust values based on the specific case
        userDAO.setReceiveHeartCount(50);
        userDAO.setPostCount(20);
        userDAO.setPetCount(5);
        userDAO.setDiaryCount(100);
        userDAO.setFollowerCount(30);

        // Mocking the achievements
        List<AchievementDAO> achievementDAOs = new ArrayList<>();

        AchievementDAO achievementDAO = new AchievementDAO();
        achievementDAO.setAchievement(Achievement.공감왕);
        achievementDAO.setAchievementName("공감왕");
        achievementDAO.setAchievementExplain("Explanation 1");
        achievementDAO.setIcon("Icon");
        achievementDAO.setStyle("Style");
        achievementDAOs.add(achievementDAO);

        AchievementDAO achievementDAO1 = new AchievementDAO();
        achievementDAO1.setAchievement(Achievement.공감왕);
        achievementDAO1.setAchievementName("공감왕");
        achievementDAO1.setAchievementExplain("Explanation 1");
        achievementDAO1.setIcon("Icon");
        achievementDAO1.setStyle("Style");
        achievementDAOs.add(achievementDAO1);

        AchievementDAO achievementDAO2 = new AchievementDAO();
        achievementDAO2.setAchievement(Achievement.공감왕);
        achievementDAO2.setAchievementName("공감왕");
        achievementDAO2.setAchievementExplain("Explanation 1");
        achievementDAO2.setIcon("Icon");
        achievementDAO2.setStyle("Style");
        achievementDAOs.add(achievementDAO2);

        // Add more achievements as needed
        when(achievementRepositoryJPA.findAll()).thenReturn(achievementDAOs);

        // Test
        List<ResponseAchievementDTO> result = getAchievementsDAOBean.exec(0L, userDAO);

        // Verify that the correct achievements are returned
        assertEquals(3, result.size());  // Adjust the expected count based on the number of in-progress achievements

        // Verify that the correct achievements are included in the result
        for (ResponseAchievementDTO responseAchievementDTO : result) {
            // Adjust the conditions based on the in-progress achievements
            switch (responseAchievementDTO.getAchievement()) {
                case 공감왕:
                    assertEquals(100, responseAchievementDTO.getScore());
                    // Adjust other assertions based on the specific case
                    break;
                case 이구역인싸:
                    assertEquals(Grade.BRONZE.name(), responseAchievementDTO.getGrade());
                    // Adjust other assertions based on the specific case
                    break;
                case 멋진작가:
                    assertEquals(Grade.SILVER.name(), responseAchievementDTO.getGrade());
                    // Adjust other assertions based on the specific case
                    break;
            }
        }
    }
}