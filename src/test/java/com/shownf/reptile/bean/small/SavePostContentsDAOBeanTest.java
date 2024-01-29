package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.repository.PostContentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SavePostContentsDAOBeanTest {

    @Mock
    private PostContentRepositoryJPA postContentRepositoryJPA;

    @Mock
    private CreateUniqueIdBean createUniqueIdBean;

    @InjectMocks
    private SavePostContentsDAOBean savePostContentsDAOBean;

    @Test
    void exec_shouldSavePostContentToRepository() {
        // Given
        PostContentDAO postContentToSave = new PostContentDAO();
        postContentToSave.setPostContentId(1L);

        ArgumentCaptor<PostContentDAO> postContentDAOArgumentCaptor = ArgumentCaptor.forClass(PostContentDAO.class);


        // When
        savePostContentsDAOBean.exec(postContentToSave);

        // Then
        // Verify that the save method of postContentRepositoryJPA is called with the correct argument
        verify(postContentRepositoryJPA, times(1)).save(postContentDAOArgumentCaptor.capture());

        // 캡처한 인자로 추가적인 어서션 수행
        PostContentDAO capturedPostContentDAO = postContentDAOArgumentCaptor.getValue();
        assertThat(capturedPostContentDAO.getPostContentId()).isEqualTo(postContentToSave.getPostContentId());
    }

    @Test
    void exec_shouldSaveAllPostContentsToRepository() {
        // Given
        List<PostContentDAO> postContentListToSave = Arrays.asList(
                new PostContentDAO(1L, 1L, "image1.jpg", "Content 1", 1, false),
                new PostContentDAO(2L, 1L, "image2.jpg", "Content 2", 2, false)
        );

        ArgumentCaptor<PostContentDAO> postContentDAOArgumentCaptor = ArgumentCaptor.forClass(PostContentDAO.class);

        // When
        savePostContentsDAOBean.exec(postContentListToSave);

        // Then
        // Verify that the save method of postContentRepositoryJPA is called for each postContent in the list
        verify(postContentRepositoryJPA, times(postContentListToSave.size())).save(postContentDAOArgumentCaptor.capture());

        // Extract the captured PostContentDAO instances
        List<PostContentDAO> capturedPostContentDAOs = postContentDAOArgumentCaptor.getAllValues();

        // Perform assertions for each PostContentDAO instance
        for (int i = 0; i < postContentListToSave.size(); i++) {
            PostContentDAO expectedPostContent = postContentListToSave.get(i);
            PostContentDAO capturedPostContentDAO = capturedPostContentDAOs.get(i);

            // Assertions for individual fields
            assertThat(capturedPostContentDAO.getPostId()).isEqualTo(expectedPostContent.getPostId());
            assertThat(capturedPostContentDAO.getImageUrl()).isEqualTo(expectedPostContent.getImageUrl());
            assertThat(capturedPostContentDAO.getContent()).isEqualTo(expectedPostContent.getContent());
            assertThat(capturedPostContentDAO.getPostContentIndex()).isEqualTo(expectedPostContent.getPostContentIndex());
            assertThat(capturedPostContentDAO.isDeleteCheck()).isEqualTo(expectedPostContent.isDeleteCheck());
        }
    }

    @Test
    void exec_shouldCreateAndSavePostContentDAO() {
        // Given
        Long postId = 1L;
        RequestPostSaveDTO requestPostSaveDTO = new RequestPostSaveDTO();
        requestPostSaveDTO.setContent(Arrays.asList(
                Collections.singletonMap("postContentIndex", "1"),
                Collections.singletonMap("postContentIndex", "2")
        ));

        when(createUniqueIdBean.exec()).thenReturn(100L).thenReturn(101L);

        // When
        List<Map<Integer, Long>> savedPostContents = savePostContentsDAOBean.exec(postId, requestPostSaveDTO);

        // Then
        assertThat(savedPostContents).hasSize(requestPostSaveDTO.getContent().size());

        // Verify that the save method of postContentRepositoryJPA is called with the correct arguments
        verify(postContentRepositoryJPA, times(requestPostSaveDTO.getContent().size()))
                .save(Mockito.any(PostContentDAO.class));
    }
}