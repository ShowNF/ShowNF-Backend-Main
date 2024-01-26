package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.repository.CommentHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaveCommentHeartDAOBeanTest {

    @Mock
    CommentHeartRepositoryJPA commentHeartRepositoryJPA;

    @InjectMocks
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;

    @Test
    void exec_shouldSaveCommentHeart() {
        // Given
        CommentHeartDAO commentHeartToSave = new CommentHeartDAO();
        commentHeartToSave.setCommentHeartId(1L);
        commentHeartToSave.setUserId(1L);

        // When
        saveCommentHeartDAOBean.exec(commentHeartToSave);

        // Then
        // Use ArgumentCaptor to capture the argument passed to the save method
        ArgumentCaptor<CommentHeartDAO> commentHeartCaptor = ArgumentCaptor.forClass(CommentHeartDAO.class);
        verify(commentHeartRepositoryJPA, times(1)).save(commentHeartCaptor.capture());

        // Verify that the saved comment heart has the expected properties
        CommentHeartDAO savedCommentHeart = commentHeartCaptor.getValue();

        assertThat(savedCommentHeart.getCommentHeartId()).isEqualTo(commentHeartToSave.getCommentHeartId());
        assertThat(savedCommentHeart.getUserId()).isEqualTo(commentHeartToSave.getUserId());
    }
}