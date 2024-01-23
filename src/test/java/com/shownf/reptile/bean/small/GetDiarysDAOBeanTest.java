package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetDiarysDAOBeanTest {

    @Mock
    private DiaryRepositoryJPA diaryRepositoryJPA;

    @InjectMocks
    private GetDiarysDAOBean getDiarysDAOBean;

    @Test
    void exec_withPetIdAndYearMonth_shouldReturnDiaryDAOsForGivenMonth() {
        // Given
        Long petId = 1L;
        String yearMonth = "202401";
        List<DiaryDAO> expectedDiaryDAOs = new ArrayList<>();

        DiaryDAO diaryDAO = new DiaryDAO();
        diaryDAO.setDiaryId(1L);
        diaryDAO.setPetId(100L);
        diaryDAO.setImageUrl("image-url.jpg");
        diaryDAO.setFood("Meal");
        diaryDAO.setFoodCounter(2);
        diaryDAO.setSize("Large");
        diaryDAO.setWeight(10.5);
        diaryDAO.setMemo("Test diary memo");
        diaryDAO.setUploadTime(LocalDateTime.now());
        diaryDAO.setUpdateTime(LocalDateTime.now());
        diaryDAO.setDate("2024 1 1");
        diaryDAO.setMonth("202401");
        diaryDAO.setEcdysis(true);
        diaryDAO.setCleaning(false);
        diaryDAO.setShower(true);
        diaryDAO.setBowelMovement(false);

        DiaryDAO diaryDAO1 = new DiaryDAO();
        diaryDAO1.setDiaryId(2L);
        diaryDAO1.setPetId(101L);
        diaryDAO1.setImageUrl("image-url.jpg");
        diaryDAO1.setFood("Meal");
        diaryDAO1.setFoodCounter(2);
        diaryDAO1.setSize("Large");
        diaryDAO1.setWeight(10.5);
        diaryDAO1.setMemo("Test diary memo");
        diaryDAO1.setUploadTime(LocalDateTime.now());
        diaryDAO1.setUpdateTime(LocalDateTime.now());
        diaryDAO1.setDate("2024 1 1");
        diaryDAO1.setMonth("202401");
        diaryDAO1.setEcdysis(true);
        diaryDAO1.setCleaning(false);
        diaryDAO1.setShower(true);
        diaryDAO1.setBowelMovement(false);

        expectedDiaryDAOs.add(diaryDAO);
        expectedDiaryDAOs.add(diaryDAO1);

        // Mock the behavior of DiaryRepositoryJPA
        when(diaryRepositoryJPA.findByPetId(petId)).thenReturn(expectedDiaryDAOs);

        // When
        List<DiaryDAO> result = getDiarysDAOBean.exec(petId, yearMonth);

        // Then
        assertThat(result).isNotNull().hasSize(2);

        // Verify that the findByPetId method of DiaryRepositoryJPA was called with the correct argument
        verify(diaryRepositoryJPA, times(1)).findByPetId(petId);
    }

    @Test
    void exec_withPetDAO_shouldReturnDiaryDAOsAndSetDeleteCheck() {
        // Given
        PetDAO petDAO = new PetDAO();
        petDAO.setPetId(1L);
        List<DiaryDAO> expectedDiaryDAOs = new ArrayList<>();
        expectedDiaryDAOs.add(new DiaryDAO());
        expectedDiaryDAOs.add(new DiaryDAO());

        // Mock the behavior of DiaryRepositoryJPA
        when(diaryRepositoryJPA.findByPetId(petDAO.getPetId())).thenReturn(expectedDiaryDAOs);

        // When
        List<DiaryDAO> result = getDiarysDAOBean.exec(petDAO);

        // Then
        assertThat(result).isNotNull().hasSize(2);

        // Verify that the findByPetId method of DiaryRepositoryJPA was called with the correct argument
        verify(diaryRepositoryJPA, times(1)).findByPetId(petDAO.getPetId());

        // Verify that the deleteCheck of each DiaryDAO was set to true
        for (DiaryDAO diaryDAO : result) {
            assertThat(diaryDAO.isDeleteCheck()).isTrue();
        }
    }
}