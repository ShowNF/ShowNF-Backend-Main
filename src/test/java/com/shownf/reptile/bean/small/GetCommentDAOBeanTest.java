package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetCommentDAOBeanTest {

    @Mock
    private CommentRepositoryJPA commentRepositoryJPA;

    @InjectMocks
    private GetCommentDAOBean getCommentDAOBean;

    @Test
    void exec_shouldReturnCommentDAOIfExists() {
        // Given
        Long commentId = 1L;
        CommentDAO expectedCommentDAO = new CommentDAO();
        expectedCommentDAO.setCommentId(commentId);

        // Mock the behavior of CommentRepositoryJPA
        when(commentRepositoryJPA.findById(commentId)).thenReturn(Optional.of(expectedCommentDAO));

        // When
        CommentDAO result = getCommentDAOBean.exec(commentId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getCommentId()).isEqualTo(commentId);

        // Verify that the findById method of CommentRepositoryJPA was called with the correct argument
        verify(commentRepositoryJPA, times(1)).findById(commentId);
    }

    @Test
    void exec_shouldReturnNullIfCommentDAODoesNotExist() {
        // Given
        Long commentId = 1L;

        // Mock the behavior of CommentRepositoryJPA
        when(commentRepositoryJPA.findById(commentId)).thenReturn(Optional.empty());

        // When
        CommentDAO result = getCommentDAOBean.exec(commentId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of CommentRepositoryJPA was called with the correct argument
        verify(commentRepositoryJPA, times(1)).findById(commentId);
    }
}