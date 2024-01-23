package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.repository.CommentHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetCommentHeartsDAOBeanTest {

    @Mock
    private CommentHeartRepositoryJPA commentHeartRepositoryJPA;

    @InjectMocks
    private GetCommentHeartsDAOBean getCommentHeartsDAOBean;

    @Test
    void exec_shouldReturnCommentHeartsList() {
        // Given
        Long userId = 1L;

        CommentHeartDAO commentHeartDAO1 = new CommentHeartDAO();
        commentHeartDAO1.setUserId(userId);
        commentHeartDAO1.setCommentId(2L);

        CommentHeartDAO commentHeartDAO2 = new CommentHeartDAO();
        commentHeartDAO2.setUserId(userId);
        commentHeartDAO2.setCommentId(3L);

        List<CommentHeartDAO> expectedCommentHearts = Arrays.asList(commentHeartDAO1, commentHeartDAO2);

        // Mock the behavior of CommentHeartRepositoryJPA
        when(commentHeartRepositoryJPA.findByUserId(userId)).thenReturn(expectedCommentHearts);

        // When
        List<CommentHeartDAO> result = getCommentHeartsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(expectedCommentHearts);

        // Verify that the findByUserId method of CommentHeartRepositoryJPA was called with the correct argument
        verify(commentHeartRepositoryJPA, times(1)).findByUserId(userId);
    }
}