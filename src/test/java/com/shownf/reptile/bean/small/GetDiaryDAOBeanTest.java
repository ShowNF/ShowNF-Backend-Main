package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetDiaryDAOBeanTest {

    @Mock
    private DiaryRepositoryJPA diaryRepositoryJPA;

    @InjectMocks
    private GetDiaryDAOBean getDiaryDAOBean;

    @Test
    void exec_withDiaryId_shouldReturnDiaryDAOIfExists() {
        // Given
        Long diaryId = 1L;
        DiaryDAO expectedDiaryDAO = new DiaryDAO();
        expectedDiaryDAO.setDiaryId(diaryId);

        // Mock the behavior of DiaryRepositoryJPA
        when(diaryRepositoryJPA.findById(diaryId)).thenReturn(Optional.of(expectedDiaryDAO));

        // When
        DiaryDAO result = getDiaryDAOBean.exec(diaryId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getDiaryId()).isEqualTo(diaryId);

        // Verify that the findById method of DiaryRepositoryJPA was called with the correct argument
        verify(diaryRepositoryJPA, times(1)).findById(diaryId);
    }

    @Test
    void exec_withNonExistingDiaryId_shouldReturnNull() {
        // Given
        Long nonExistingDiaryId = 2L;

        // Mock the behavior of DiaryRepositoryJPA
        when(diaryRepositoryJPA.findById(nonExistingDiaryId)).thenReturn(Optional.empty());

        // When
        DiaryDAO result = getDiaryDAOBean.exec(nonExistingDiaryId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of DiaryRepositoryJPA was called with the correct argument
        verify(diaryRepositoryJPA, times(1)).findById(nonExistingDiaryId);
    }
}