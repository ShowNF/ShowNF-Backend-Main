package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SavePostDAOBeanTest {

    @Mock
    private PostRepositoryJPA postRepositoryJPA;

    @InjectMocks
    private SavePostDAOBean savePostDAOBean;

    @Test
    void exec_shouldSavePostToRepository() {
        // Given
        PostDAO postToSave = new PostDAO();
        postToSave.setPostId(1L);

        ArgumentCaptor<PostDAO> postDAOArgumentCaptor = ArgumentCaptor.forClass(PostDAO.class);

        // When
        savePostDAOBean.exec(postToSave);

        // Then
        // Verify that the save method of postRepositoryJPA is called with the correct argument
        verify(postRepositoryJPA, times(1)).save(postDAOArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        PostDAO capturedPostDAO = postDAOArgumentCaptor.getValue();
        assertThat(capturedPostDAO.getPostId()).isEqualTo(postToSave.getPostId());
    }

    @Test
    void exec_shouldCreateAndSavePostDAO() {
        // Given
        Long postId = 1L;
        RequestPostSaveDTO requestPostSaveDTO = new RequestPostSaveDTO();
        requestPostSaveDTO.setUserId(1L);
        requestPostSaveDTO.setTitle("Test Post");
        requestPostSaveDTO.setCategory("TURTLE");

        List<Map<Integer, Long>> postContentIndex = Arrays.asList(
                Collections.singletonMap(0, 101L),
                Collections.singletonMap(1, 102L)
        );

        // When
        savePostDAOBean.exec(postId, requestPostSaveDTO, postContentIndex);

        // Then
        // Verify that the save method of postRepositoryJPA is called with the correct argument
        ArgumentCaptor<PostDAO> postDAOArgumentCaptor = ArgumentCaptor.forClass(PostDAO.class);
        verify(postRepositoryJPA, times(1)).save(postDAOArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        PostDAO capturedPostDAO = postDAOArgumentCaptor.getValue();
        assertThat(capturedPostDAO.getPostId()).isEqualTo(postId);
        assertThat(capturedPostDAO.getUserId()).isEqualTo(requestPostSaveDTO.getUserId());
        assertThat(capturedPostDAO.getTitle()).isEqualTo(requestPostSaveDTO.getTitle());

        // Verify that the content is correctly serialized
        assertThat(capturedPostDAO.getContent()).isEqualTo("[{\"0\":101},{\"1\":102}]");
    }
}