package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.repository.PostLogRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SavePostLogDAOBeanTest {

    @Mock
    private GetPostLogsDAOBean getPostLogsDAOBean;

    @Mock
    private PostLogRepositoryJPA postLogRepositoryJPA;

    @Mock
    private CreateUniqueIdBean createUniqueIdBean;

    @InjectMocks
    private SavePostLogDAOBean savePostLogDAOBean;

    @Test
    void exec_savePostLog() {
        // Given
        Long postId = 1L;
        Long userId = 123L;

        when(createUniqueIdBean.exec()).thenReturn(100L);

        // When
        savePostLogDAOBean.exec(postId, userId);

        // Then
        ArgumentCaptor<PostLogDAO> postLogDAOArgumentCaptor = ArgumentCaptor.forClass(PostLogDAO.class);
        // Verify that the save method of postLogRepositoryJPA is called with the correct argument
        verify(postLogRepositoryJPA, times(1)).save(postLogDAOArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        PostLogDAO capturedPostLogDAO = postLogDAOArgumentCaptor.getValue();
        assertThat(capturedPostLogDAO.getPostId()).isEqualTo(postId);
        assertThat(capturedPostLogDAO.getUserId()).isEqualTo(userId);
    }

    @Test
    void exec_updateExistingPostLog() {
        // Given
        Long postId = 1L;
        Long userId = 123L;

        PostLogDAO existingPostLog = new PostLogDAO(1L, postId, userId, LocalDateTime.now());

        when(getPostLogsDAOBean.exec(userId)).thenReturn(Arrays.asList(existingPostLog));

        // When
        savePostLogDAOBean.exec(postId, userId);

        // Then
        verify(postLogRepositoryJPA, times(1)).save(Mockito.any(PostLogDAO.class));

        // Ensure that the viewTime of the existingPostLog is updated
        assertThat(existingPostLog.getViewTime()).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void exec_deleteOldestPostLog() {
        // Given
        Long postId = 1L;
        Long userId = 123L;

        List<PostLogDAO> existingPostLogs = Arrays.asList(
                new PostLogDAO(1L, 1L, userId, LocalDateTime.now().minusDays(5)),
                new PostLogDAO(2L, 2L, userId, LocalDateTime.now().minusDays(3)),
                new PostLogDAO(3L, postId, userId, LocalDateTime.now().minusDays(1))
        );

        when(getPostLogsDAOBean.exec(userId)).thenReturn(existingPostLogs);
        when(createUniqueIdBean.exec()).thenReturn(100L);

        // When
        savePostLogDAOBean.exec(postId, userId);

        // Then
        verify(postLogRepositoryJPA, times(1)).save(Mockito.any(PostLogDAO.class));
    }

    @Test
    void exec_doNotDeletePostLogWhenNotEnoughLogs() {
        // Given
        Long postId = 1L;
        Long userId = 123L;

        List<PostLogDAO> existingPostLogs = Arrays.asList(
                new PostLogDAO(1L, 1L, userId, LocalDateTime.now().minusDays(5)),
                new PostLogDAO(2L, 2L, userId, LocalDateTime.now().minusDays(3))
        );

        when(getPostLogsDAOBean.exec(userId)).thenReturn(existingPostLogs);
        when(createUniqueIdBean.exec()).thenReturn(100L);

        // When
        savePostLogDAOBean.exec(postId, userId);

        // Then
        verify(postLogRepositoryJPA, times(1)).save(Mockito.any(PostLogDAO.class));
        verify(postLogRepositoryJPA, never()).delete(Mockito.any(PostLogDAO.class));
    }

    @Test
    void exec_skipExecutionForNullUserId() {
        // Given
        Long postId = 1L;
        Long userId = null;

        // When
        savePostLogDAOBean.exec(postId, userId);

        // Then
        verify(postLogRepositoryJPA, never()).save(Mockito.any(PostLogDAO.class));
    }
}