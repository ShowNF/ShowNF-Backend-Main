package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.repository.ReplyRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetReplyDAOBeanTest {

    @Mock
    ReplyRepositoryJPA replyRepositoryJPA;

    @InjectMocks
    GetReplyDAOBean getReplyDAOBean;

    @Test
    void exec_withValidReplyId_shouldReturnReplyDAO() {
        // Given
        Long replyId = 1L;
        ReplyDAO expectedReplyDAO = new ReplyDAO();
        expectedReplyDAO.setReplyId(replyId);

        // Mock the behavior of ReplyRepositoryJPA
        when(replyRepositoryJPA.findById(replyId)).thenReturn(Optional.of(expectedReplyDAO));

        // When
        ReplyDAO result = getReplyDAOBean.exec(replyId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedReplyDAO);

        // Verify that the findById method of ReplyRepositoryJPA was called with the correct argument
        verify(replyRepositoryJPA, times(1)).findById(replyId);
    }

    @Test
    void exec_withInvalidReplyId_shouldReturnNull() {
        // Given
        Long replyId = 2L;

        // Mock the behavior of ReplyRepositoryJPA
        when(replyRepositoryJPA.findById(replyId)).thenReturn(Optional.empty());

        // When
        ReplyDAO result = getReplyDAOBean.exec(replyId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of ReplyRepositoryJPA was called with the correct argument
        verify(replyRepositoryJPA, times(1)).findById(replyId);
    }
}