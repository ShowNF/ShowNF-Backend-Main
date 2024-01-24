package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.repository.PostContentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostContentDAOBeanTest {

    @Mock
    private PostContentRepositoryJPA postContentRepositoryJPA;

    @InjectMocks
    private GetPostContentDAOBean getPostContentDAOBean;

    @Test
    void exec_withPostContentId_shouldReturnPostContentDAOIfExists() {
        // Given
        Long postContentId = 1L;
        PostContentDAO expectedPostContentDAO = new PostContentDAO();
        expectedPostContentDAO.setPostContentId(postContentId);

        // Mock the behavior of PostContentRepositoryJPA
        when(postContentRepositoryJPA.findById(postContentId)).thenReturn(Optional.of(expectedPostContentDAO));

        // When
        PostContentDAO result = getPostContentDAOBean.exec(postContentId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPostContentId()).isEqualTo(postContentId);

        // Verify that the findById method of PostContentRepositoryJPA was called with the correct argument
        verify(postContentRepositoryJPA, times(1)).findById(postContentId);
    }

    @Test
    void exec_withNonExistingPostContent_shouldReturnNull() {
        // Given
        Long nonExistingPostContentId = 2L;

        // Mock the behavior of PostContentRepositoryJPA
        when(postContentRepositoryJPA.findById(nonExistingPostContentId)).thenReturn(Optional.empty());

        // When
        PostContentDAO result = getPostContentDAOBean.exec(nonExistingPostContentId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of PostContentRepositoryJPA was called with the correct argument
        verify(postContentRepositoryJPA, times(1)).findById(nonExistingPostContentId);
    }
}