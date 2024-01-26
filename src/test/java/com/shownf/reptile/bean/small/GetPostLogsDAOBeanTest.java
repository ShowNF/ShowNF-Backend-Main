package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.repository.PostLogRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostLogsDAOBeanTest {

    @Mock
    PostLogRepositoryJPA postLogRepositoryJPA;

    @InjectMocks
    GetPostLogsDAOBean getPostLogsDAOBean;

    @Test
    void exec_withUserId_shouldReturnPostLogs() {
        // Given
        Long userId = 1L;
        List<PostLogDAO> expectedPostLogs = new ArrayList<>();
        expectedPostLogs.add(new PostLogDAO(1L, userId, 101L, LocalDateTime.now()));
        expectedPostLogs.add(new PostLogDAO(2L, userId, 102L, LocalDateTime.now()));

        // Mock the behavior of PostLogRepositoryJPA
        when(postLogRepositoryJPA.findByUserId(userId, Sort.by(Sort.Order.desc("viewTime"))))
                .thenReturn(expectedPostLogs);

        // When
        List<PostLogDAO> result = getPostLogsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(expectedPostLogs);

        // Verify that the findByUserId method of PostLogRepositoryJPA was called with the correct argument
        verify(postLogRepositoryJPA, times(1)).findByUserId(userId, Sort.by(Sort.Order.desc("viewTime")));
    }

    @Test
    void exec_withUserIdAndEmptyList_shouldReturnEmptyList() {
        // Given
        Long userId = 2L;

        // Mock the behavior of PostLogRepositoryJPA
        when(postLogRepositoryJPA.findByUserId(userId, Sort.by(Sort.Order.desc("viewTime"))))
                .thenReturn(new ArrayList<>());

        // When
        List<PostLogDAO> result = getPostLogsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();

        // Verify that the findByUserId method of PostLogRepositoryJPA was called with the correct argument
        verify(postLogRepositoryJPA, times(1)).findByUserId(userId, Sort.by(Sort.Order.desc("viewTime")));
    }
}