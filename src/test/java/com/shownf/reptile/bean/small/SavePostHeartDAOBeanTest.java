package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.repository.PostHeartRepositoryJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SavePostHeartDAOBeanTest {

    @Mock
    private PostHeartRepositoryJPA postHeartRepositoryJPA;

    @InjectMocks
    private SavePostHeartDAOBean savePostHeartDAOBean;

    @Test
    void exec_shouldSavePostHeartToRepository() {
        // Given
        PostHeartDAO postHeartToSave = new PostHeartDAO();
        postHeartToSave.setPostId(1L);

        ArgumentCaptor<PostHeartDAO> postHeartDAOArgumentCaptor = ArgumentCaptor.forClass(PostHeartDAO.class);

        // When
        savePostHeartDAOBean.exec(postHeartToSave);

        // Then
        // Verify that the save method of postHeartRepositoryJPA is called with the correct argument
        verify(postHeartRepositoryJPA, times(1)).save(postHeartDAOArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        PostHeartDAO capturedPostHeartDAO = postHeartDAOArgumentCaptor.getValue();
        assertThat(capturedPostHeartDAO.getPostId()).isEqualTo(postHeartToSave.getPostId());
    }
}