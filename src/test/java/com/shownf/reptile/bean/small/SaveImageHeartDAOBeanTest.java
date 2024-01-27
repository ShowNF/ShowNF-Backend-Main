package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageHeartDAO;
import com.shownf.reptile.repository.ImageHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class SaveImageHeartDAOBeanTest {

    @Mock
    ImageHeartRepositoryJPA imageHeartRepositoryJPA;

    @InjectMocks
    SaveImageHeartDAOBean saveImageHeartDAOBean;

    @Test
    void exec_shouldSaveImageHeart() {
        // Given
        ImageHeartDAO imageHeartToSave = new ImageHeartDAO();
        imageHeartToSave.setImageHeartId(1L);
        imageHeartToSave.setUserId(1L);

        // When
        saveImageHeartDAOBean.exec(imageHeartToSave);

        // Then
        // Verify that imageHeartRepositoryJPA.save is called with the correct argument
        verify(imageHeartRepositoryJPA, times(1)).save(imageHeartToSave);
    }
}