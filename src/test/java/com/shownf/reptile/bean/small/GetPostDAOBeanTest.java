package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostDAOBeanTest {

    @Mock
    private PostRepositoryJPA postRepositoryJPA;

    @InjectMocks
    private GetPostDAOBean getPostDAOBean;

    @Test
    void exec_withExistingPostId_shouldReturnPostDAO() {
        // Given
        Long postId = 1L;
        PostDAO expectedPostDAO = new PostDAO();
        expectedPostDAO.setPostId(postId);
        expectedPostDAO.setTitle("Test Title");
        expectedPostDAO.setContent("[{\"0\":1}]");

        // Mock the behavior of PostRepositoryJPA
        when(postRepositoryJPA.findById(postId)).thenReturn(Optional.of(expectedPostDAO));

        // When
        PostDAO result = getPostDAOBean.exec(postId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPostId()).isEqualTo(postId);
        assertThat(result.getTitle()).isEqualTo("Test Title");
        assertThat(result.getContent()).isEqualTo("[{\"0\":1}]");

        // Verify that the findById method of PostRepositoryJPA was called with the correct argument
        verify(postRepositoryJPA, times(1)).findById(postId);
    }

    @Test
    void exec_withNonExistingPostId_shouldReturnNull() {
        // Given
        Long nonExistingPostId = 2L;

        // Mock the behavior of PostRepositoryJPA
        when(postRepositoryJPA.findById(nonExistingPostId)).thenReturn(Optional.empty());

        // When
        PostDAO result = getPostDAOBean.exec(nonExistingPostId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of PostRepositoryJPA was called with the correct argument
        verify(postRepositoryJPA, times(1)).findById(nonExistingPostId);
    }
}