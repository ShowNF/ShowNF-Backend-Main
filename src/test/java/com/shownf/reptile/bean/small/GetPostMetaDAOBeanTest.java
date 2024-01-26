package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostMetaDAOBeanTest {

    private final PostMetaRepositoryJPA postMetaRepositoryJPA = mock(PostMetaRepositoryJPA.class);
    private final GetPostMetaDAOBean getPostMetaDAOBean = new GetPostMetaDAOBean(postMetaRepositoryJPA);

    @Test
    void exec_withValidPostId_shouldReturnPostMeta() {
        // Given
        Long postId = 1L;
        PostMeta expectedPostMeta = new PostMeta();
        expectedPostMeta.setPostId(postId);

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findById(postId)).thenReturn(Optional.of(expectedPostMeta));

        // When
        PostMeta result = getPostMetaDAOBean.exec(postId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPostMeta);

        // Verify that the findById method of PostMetaRepositoryJPA was called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).findById(postId);
    }

    @Test
    void exec_withInvalidPostId_shouldReturnNull() {
        // Given
        Long postId = 2L;

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findById(postId)).thenReturn(Optional.empty());

        // When
        PostMeta result = getPostMetaDAOBean.exec(postId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of PostMetaRepositoryJPA was called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).findById(postId);
    }

    @Test
    void exec_withValidPostIds_shouldReturnListOfPostMetas() {
        // Given
        List<Long> postIds = Arrays.asList(1L, 2L, 3L);
        List<PostMeta> expectedPostMetas = new ArrayList<>();

        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(1L);
        expectedPostMetas.add(postMeta);

        PostMeta postMeta1 = new PostMeta();
        postMeta1.setPostId(2L);
        expectedPostMetas.add(postMeta1);

        PostMeta postMeta2 = new PostMeta();
        postMeta2.setPostId(3L);
        expectedPostMetas.add(postMeta2);

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findAllById(postIds)).thenReturn(expectedPostMetas);

        // When
        List<PostMeta> result = getPostMetaDAOBean.exec(postIds);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result).isEqualTo(expectedPostMetas);

        // Verify that the findAllById method of PostMetaRepositoryJPA was called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).findAllById(postIds);
    }

    @Test
    void exec_withEmptyPostIds_shouldReturnEmptyList() {
        // Given
        List<Long> postIds = Arrays.asList();

        // Mock the behavior of PostMetaRepositoryJPA
        when(postMetaRepositoryJPA.findAllById(postIds)).thenReturn(Arrays.asList());

        // When
        List<PostMeta> result = getPostMetaDAOBean.exec(postIds);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        // Verify that the findAllById method of PostMetaRepositoryJPA was called with the correct argument
        verify(postMetaRepositoryJPA, times(1)).findAllById(postIds);
    }
}