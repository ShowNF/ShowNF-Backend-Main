package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaveCommentDAOBeanTest {

    @Mock
    CommentRepositoryJPA commentRepositoryJPA;

    @InjectMocks
    SaveCommentDAOBean saveCommentDAOBean;

    @Test
    void exec_shouldSaveComment() {
        // Given
        CommentDAO commentToSave = new CommentDAO();
        commentToSave.setCommentId(1L);
        commentToSave.setUserId(1L);

        // When
        saveCommentDAOBean.exec(commentToSave);

        // Then
        // Use ArgumentCaptor to capture the argument passed to the save method
        ArgumentCaptor<CommentDAO> commentCaptor = ArgumentCaptor.forClass(CommentDAO.class);
        verify(commentRepositoryJPA, times(1)).save(commentCaptor.capture());

        // Verify that the saved comment has the expected properties
        CommentDAO savedComment = commentCaptor.getValue();

        assertThat(savedComment).isEqualTo(commentToSave);
    }
}