package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageDAO;
import com.shownf.reptile.repository.ImageRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class SaveImageDAOBeanTest {

    @Mock
    ImageRepositoryJPA imageRepositoryJPA;

    @InjectMocks
    SaveImageDAOBean saveImageDAOBean;

    @Test
    void exec_shouldSaveImage() {
        // Given
        ImageDAO imageToSave = new ImageDAO();
        imageToSave.setImageId(1L);
        imageToSave.setImageUrl("image-url");

        // When
        saveImageDAOBean.exec(imageToSave);

        // Then
        // Verify that imageRepositoryJPA.save is called with the correct argument
        verify(imageRepositoryJPA, times(1)).save(imageToSave);
    }
}