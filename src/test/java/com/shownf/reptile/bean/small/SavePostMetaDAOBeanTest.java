package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SavePostMetaDAOBeanTest {

    @Mock
    private PostMetaRepositoryJPA postMetaRepositoryJPA;

    @InjectMocks
    private SavePostMetaDAOBean savePostMetaDAOBean;

    @Test
    void exec_savePostMeta() {
        // Given
        long postId = 1L;
        long userId = 123L;

        RequestPostSaveDTO requestPostSaveDTO = new RequestPostSaveDTO();
        requestPostSaveDTO.setUserId(userId);
        requestPostSaveDTO.setTitle("Test Post");
        requestPostSaveDTO.setCategory("TURTLE");

        List<Map<Integer, Long>> postContentIndex = Collections.singletonList(Collections.singletonMap(0, 101L));

        // When
        savePostMetaDAOBean.exec(postId, requestPostSaveDTO, postContentIndex);

        // Then
        ArgumentCaptor<PostMeta> postMetaArgumentCaptor = ArgumentCaptor.forClass(PostMeta.class);
        // Verify that the save method of postMetaRepositoryJPA is called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).save(postMetaArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        PostMeta capturedPostMeta = postMetaArgumentCaptor.getValue();
        assertThat(capturedPostMeta.getPostId()).isEqualTo(postId);
        assertThat(capturedPostMeta.getUserId()).isEqualTo(userId);
        assertThat(capturedPostMeta.getTitle()).isEqualTo(requestPostSaveDTO.getTitle());
        assertThat(capturedPostMeta.getCategory()).isEqualTo(Category.valueOf("TURTLE"));
    }

    @Test
    void exec_savePostMetaWithEmptyPostContentIndex() {
        // Given
        long postId = 1L;
        long userId = 123L;

        RequestPostSaveDTO requestPostSaveDTO = new RequestPostSaveDTO();
        requestPostSaveDTO.setUserId(userId);
        requestPostSaveDTO.setTitle("Test Post");
        requestPostSaveDTO.setCategory("TURTLE");

        List<Map<Integer, Long>> emptyPostContentIndex = Collections.emptyList();

        // When
        savePostMetaDAOBean.exec(postId, requestPostSaveDTO, emptyPostContentIndex);

        // Then
        ArgumentCaptor<PostMeta> postMetaArgumentCaptor = ArgumentCaptor.forClass(PostMeta.class);
        // Verify that the save method of postMetaRepositoryJPA is called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).save(postMetaArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        PostMeta capturedPostMeta = postMetaArgumentCaptor.getValue();
        assertThat(capturedPostMeta.getPostId()).isEqualTo(postId);
    }
}