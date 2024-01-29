package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.repository.ReplyRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaveReplyDAOBeanTest {

    @Mock
    private ReplyRepositoryJPA replyRepositoryJPA;

    @InjectMocks
    private SaveReplyDAOBean saveReplyDAOBean;

    @Test
    void exec_saveReplyDAO() {
        // Given
        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setReplyId(1L);

        ArgumentCaptor<ReplyDAO> replyDAOArgumentCaptor = ArgumentCaptor.forClass(ReplyDAO.class);

        // When
        saveReplyDAOBean.exec(replyDAO);

        // Then
        // Verify that the save method of replyRepositoryJPA is called with the correct argument
        verify(replyRepositoryJPA, times(1)).save(replyDAOArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        ReplyDAO capturedReplyDAO = replyDAOArgumentCaptor.getValue();
        assertThat(capturedReplyDAO.getReplyId()).isEqualTo(replyDAO.getReplyId());
    }
}