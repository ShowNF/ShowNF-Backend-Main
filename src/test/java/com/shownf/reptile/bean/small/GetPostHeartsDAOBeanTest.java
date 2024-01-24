package com.shownf.reptile.bean.small;

import  com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.repository.PostHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostHeartsDAOBeanTest {

    @Mock
    private PostHeartRepositoryJPA postHeartRepositoryJPA;

    @InjectMocks
    private GetPostHeartsDAOBean getPostHeartsDAOBean;

    @Test
    void exec_withUserId_shouldReturnPostHearts() {
        // Given
        Long userId = 1L;
        List<PostHeartDAO> expectedPostHearts = new ArrayList<>();
        expectedPostHearts.add(new PostHeartDAO(1L, userId, 101L, null));
        expectedPostHearts.add(new PostHeartDAO(2L, userId, 102L, null));

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findByUserId(userId)).thenReturn(expectedPostHearts);

        // When
        List<PostHeartDAO> result = getPostHeartsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).contains(expectedPostHearts.get(0), expectedPostHearts.get(1));

        // Verify that the findByUserId method of PostHeartRepositoryJPA was called with the correct argument
        verify(postHeartRepositoryJPA, times(1)).findByUserId(userId);
    }

    @Test
    void exec_withUserId_shouldReturnEmptyList() {
        // Given
        Long userId = 2L;

        // Mock the behavior of PostHeartRepositoryJPA to return an empty list
        when(postHeartRepositoryJPA.findByUserId(userId)).thenReturn(new ArrayList<>());

        // When
        List<PostHeartDAO> result = getPostHeartsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        // Verify that the findByUserId method of PostHeartRepositoryJPA was called with the correct argument
        verify(postHeartRepositoryJPA, times(1)).findByUserId(userId);
    }
}