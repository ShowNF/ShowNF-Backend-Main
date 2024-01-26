package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostsDAOBeanTest {

    @Mock
    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @InjectMocks
    GetPostsDAOBean getPostsDAOBean;

    @Test
    void exec_withPageable_shouldReturnPageOfPostMetas() {
        // Given
        Pageable pageable = mock(Pageable.class);
        Page<PostMeta> expectedPage = mock(Page.class);

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findAll(pageable)).thenReturn(expectedPage);

        // When
        Page<PostMeta> result = getPostsDAOBean.exec(pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPage);

        // Verify that the findAll method of PostMetaRepositoryJPA was called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).findAll(pageable);
    }

    @Test
    void exec_withPostIdsAndPageable_shouldReturnPageOfPostMetas() {
        // Given
        List<Long> postIds = Arrays.asList(1L, 2L, 3L);
        Pageable pageable = mock(Pageable.class);
        Page<PostMeta> expectedPage = mock(Page.class);

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findAllByPostIdIn(postIds, pageable)).thenReturn(expectedPage);

        // When
        Page<PostMeta> result = getPostsDAOBean.exec(postIds, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPage);

        // Verify that the findAllByPostIdIn method of PostMetaRepositoryJPA was called with the correct arguments
        verify(postMetaRepositoryJPA, times(1)).findAllByPostIdIn(postIds, pageable);
    }

    @Test
    void exec_withUserIdAndPageable_shouldReturnPageOfPostMetas() {
        // Given
        Long userId = 1L;
        Pageable pageable = mock(Pageable.class);
        Page<PostMeta> expectedPage = mock(Page.class);

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findByUserId(userId, pageable)).thenReturn(expectedPage);

        // When
        Page<PostMeta> result = getPostsDAOBean.exec(userId, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPage);

        // Verify that the findByUserId method of PostMetaRepositoryJPA was called with the correct arguments
        verify(postMetaRepositoryJPA, times(1)).findByUserId(userId, pageable);
    }

    @Test
    void exec_withCategoryAndPageable_shouldReturnPageOfPostMetas() {
        // Given
        String category = "TURTLE";
        Pageable pageable = mock(Pageable.class);
        Page<PostMeta> expectedPage = mock(Page.class);

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findByCategory(Category.TURTLE, pageable)).thenReturn(expectedPage);

        // When
        Page<PostMeta> result = getPostsDAOBean.exec(category, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPage);

        // Verify that the findByCategory method of PostMetaRepositoryJPA was called with the correct arguments
        verify(postMetaRepositoryJPA, times(1)).findByCategory(Category.TURTLE, pageable);
    }
}