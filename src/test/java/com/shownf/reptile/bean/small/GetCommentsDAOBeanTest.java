package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetCommentsDAOBeanTest {

    @Mock
    private CommentRepositoryJPA commentRepositoryJPA;

    @InjectMocks
    private GetCommentsDAOBean getCommentsDAOBean;

    @Test
    void exec_shouldReturnCommentsList() {
        // Given
        Long postId = 1L;

        CommentDAO commentDAO1 = new CommentDAO();
        commentDAO1.setCommentId(1L);
        commentDAO1.setPostId(postId);
        commentDAO1.setContent("Comment 1");

        CommentDAO commentDAO2 = new CommentDAO();
        commentDAO2.setCommentId(2L);
        commentDAO2.setPostId(postId);
        commentDAO2.setContent("Comment 2");

        List<CommentDAO> expectedComments = Arrays.asList(commentDAO1, commentDAO2);

        // Mock the behavior of CommentRepositoryJPA
        when(commentRepositoryJPA.findByPostId(postId)).thenReturn(expectedComments);

        // When
        List<CommentDAO> result = getCommentsDAOBean.exec(postId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(expectedComments);

        // Verify that the findByPostId method of CommentRepositoryJPA was called with the correct argument
        verify(commentRepositoryJPA, times(1)).findByPostId(postId);
    }
}