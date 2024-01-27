package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaveDiaryDAOBeanTest {

    @Mock
    DiaryRepositoryJPA diaryRepositoryJPA;

    @InjectMocks
    SaveDiaryDAOBean saveDiaryDAOBean;

    @Test
    void exec_withDiaryDAO_shouldSaveDiaryDAO() {
        // Given
        DiaryDAO diaryToSave = new DiaryDAO();
        diaryToSave.setDiaryId(1L);

        // When
        saveDiaryDAOBean.exec(diaryToSave);

        // Then
        ArgumentCaptor<DiaryDAO> diaryDAOCaptor = ArgumentCaptor.forClass(DiaryDAO.class);
        verify(diaryRepositoryJPA, times(1)).save(diaryDAOCaptor.capture());

        // Verify that the saved comment heart has the expected properties
        DiaryDAO savedDiaryDAO = diaryDAOCaptor.getValue();

        assertThat(savedDiaryDAO.getDiaryId()).isEqualTo(savedDiaryDAO.getDiaryId());
    }

    @Test
    void exec_shouldSaveDiaries() {
        // Given
        List<DiaryDAO> diaryDAOS = Arrays.asList(
                new DiaryDAO(1L, 1L, "[{\"imageurl\":\"http://example.com/image1.jpg\"}]", "Dog Food", 2, "Medium", 10.5, "Memo1", LocalDateTime.now(), LocalDateTime.now(), "2024-01-25", "202401", true, false, true, true, false),
                new DiaryDAO(2L, 1L, "[{\"imageurl\":\"http://example.com/image2.jpg\"}]", "Cat Food", 1, "Small", 8.0, "Memo2", LocalDateTime.now(), LocalDateTime.now(), "2024-01-26", "202401", false, true, false, true, false)
        );

        // When
        saveDiaryDAOBean.exec(diaryDAOS);

        // Then
        // Verify that the save method is called for each DiaryDAO in the list
        for (DiaryDAO diaryDAO : diaryDAOS) {
            verify(diaryRepositoryJPA, times(1)).save(diaryDAO);
        }
    }

    @Test
    void exec_shouldCreateAndSaveDiaryDAO() {
        // Given
        Long diaryId = 1L;
        LocalDateTime time = LocalDateTime.now();
        RequestDiarySaveDTO requestDiarySaveDTO = new RequestDiarySaveDTO();
        requestDiarySaveDTO.setPetId(1L);

        List<Map<String, String>> imageUrl = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", "http://example.com/image.jpg");
        imageUrl.add(map);
        requestDiarySaveDTO.setImageUrl(imageUrl);
        requestDiarySaveDTO.setFood("Dog Food");
        requestDiarySaveDTO.setFoodCounter(2);
        requestDiarySaveDTO.setSize("Medium");
        requestDiarySaveDTO.setWeight(10.5);
        requestDiarySaveDTO.setMemo("Some memo");
        requestDiarySaveDTO.setDate("2024 1 25");
        requestDiarySaveDTO.setEcdysis(true);
        requestDiarySaveDTO.setCleaning(false);
        requestDiarySaveDTO.setShower(true);
        requestDiarySaveDTO.setBowelMovement(true);

        // When
        DiaryDAO createdDiaryDAO = saveDiaryDAOBean.exec(diaryId, requestDiarySaveDTO);


        // Verify that the created DiaryDAO has the expected properties
        assertThat(createdDiaryDAO).isNotNull();
        assertThat(createdDiaryDAO.getDiaryId()).isEqualTo(diaryId);
        assertThat(createdDiaryDAO.getPetId()).isEqualTo(requestDiarySaveDTO.getPetId());
        assertThat(createdDiaryDAO.getImageUrl()).isEqualTo("[{\"imageUrl\":\"http://example.com/image.jpg\"}]");
        assertThat(createdDiaryDAO.getFood()).isEqualTo(requestDiarySaveDTO.getFood());
        assertThat(createdDiaryDAO.getFoodCounter()).isEqualTo(requestDiarySaveDTO.getFoodCounter());
        assertThat(createdDiaryDAO.getSize()).isEqualTo(requestDiarySaveDTO.getSize());
        assertThat(createdDiaryDAO.getWeight()).isEqualTo(requestDiarySaveDTO.getWeight());
        assertThat(createdDiaryDAO.getMemo()).isEqualTo(requestDiarySaveDTO.getMemo());
        assertThat(createdDiaryDAO.getDate()).isEqualTo(requestDiarySaveDTO.getDate());
        assertThat(createdDiaryDAO.getMonth()).isEqualTo("202401"); // Expected value based on the logic in the exec method
        assertThat(createdDiaryDAO.isEcdysis()).isEqualTo(requestDiarySaveDTO.isEcdysis());
        assertThat(createdDiaryDAO.isCleaning()).isEqualTo(requestDiarySaveDTO.isCleaning());
        assertThat(createdDiaryDAO.isShower()).isEqualTo(requestDiarySaveDTO.isShower());
        assertThat(createdDiaryDAO.isBowelMovement()).isEqualTo(requestDiarySaveDTO.isBowelMovement());
        assertThat(createdDiaryDAO.isDeleteCheck()).isFalse();
    }
}