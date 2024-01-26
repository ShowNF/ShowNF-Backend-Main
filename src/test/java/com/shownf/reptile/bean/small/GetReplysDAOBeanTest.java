package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.repository.ReplyRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetReplysDAOBeanTest {

    @Mock
    ReplyRepositoryJPA replyRepositoryJPA;

    @InjectMocks
    GetReplysDAOBean getReplysDAOBean;

    @Test
    void exec_withCommentId_shouldReturnReplies() {
        // Given
        Long commentId = 1L;
        List<ReplyDAO> expectedReplies = new ArrayList<>();

        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setReplyId(1L);
        replyDAO.setCommentId(commentId);
        ReplyDAO replyDAO1 = new ReplyDAO();
        replyDAO1.setReplyId(2L);
        replyDAO1.setCommentId(commentId);

        expectedReplies.add(replyDAO);
        expectedReplies.add(replyDAO1);

        // Mock the behavior of ReplyRepositoryJPA
        when(replyRepositoryJPA.findByCommentId(commentId))
                .thenReturn(expectedReplies);

        // When
        List<ReplyDAO> result = getReplysDAOBean.exec(commentId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(expectedReplies);
    }

    @Test
    void exec_withCommentIdAndEmptyList_shouldReturnEmptyList() {
        // Given
        Long commentId = 2L;

        // Mock the behavior of ReplyRepositoryJPA
        when(replyRepositoryJPA.findByCommentId(commentId))
                .thenReturn(new ArrayList<>());

        // When
        List<ReplyDAO> result = getReplysDAOBean.exec(commentId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}