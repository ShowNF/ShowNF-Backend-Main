package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetDiaryRecentDAOBeanTest {

    @Mock
    private DiaryRepositoryJPA diaryRepositoryJPA;

    @InjectMocks
    private GetDiaryRecentDAOBean getDiaryRecentDAOBean;

    @Test
    void exec_withPetId_shouldReturnRecentDiaryDAOIfExists() {
        // Given
        Long petId = 1L;
        DiaryDAO expectedDiaryDAO = new DiaryDAO();
        expectedDiaryDAO.setDiaryId(1L);
        expectedDiaryDAO.setPetId(petId);
        expectedDiaryDAO.setUploadTime(LocalDateTime.now());

        // Mock the behavior of DiaryRepositoryJPA
        when(diaryRepositoryJPA.findFirstByPetIdOrderByUploadTimeDesc(petId)).thenReturn(expectedDiaryDAO);

        // When
        DiaryDAO result = getDiaryRecentDAOBean.exec(petId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPetId()).isEqualTo(petId);

        // Verify that the findFirstByPetIdOrderByUploadTimeDesc method of DiaryRepositoryJPA was called with the correct argument
        verify(diaryRepositoryJPA, times(1)).findFirstByPetIdOrderByUploadTimeDesc(petId);
    }

    @Test
    void exec_withNonExistingDiary_shouldReturnNull() {
        // Given
        Long nonExistingPetId = 2L;

        // Mock the behavior of DiaryRepositoryJPA
        when(diaryRepositoryJPA.findFirstByPetIdOrderByUploadTimeDesc(nonExistingPetId)).thenReturn(null);

        // When
        DiaryDAO result = getDiaryRecentDAOBean.exec(nonExistingPetId);

        // Then
        assertThat(result).isNull();

        // Verify that the findFirstByPetIdOrderByUploadTimeDesc method of DiaryRepositoryJPA was called with the correct argument
        verify(diaryRepositoryJPA, times(1)).findFirstByPetIdOrderByUploadTimeDesc(nonExistingPetId);
    }
}