package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.ReplyHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SaveReplyHeartDAOBeanTest {

    @Mock
    private ReplyHeartRepositoryJPA replyHeartRepositoryJPA;

    @InjectMocks
    private SaveReplyHeartDAOBean saveReplyHeartDAOBean;

    @Test
    void exec_shouldSaveReplyHeartToRepository() {
        // Given
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setReplyHeartId(1L);

        ArgumentCaptor<ReplyHeartDAO> replyHeartDAOArgumentCaptor = ArgumentCaptor.forClass(ReplyHeartDAO.class);

        // When
        saveReplyHeartDAOBean.exec(replyHeartDAO);

        // Then
        // Verify that the save method of replyHeartRepositoryJPA is called with the correct argument
        verify(replyHeartRepositoryJPA, times(1)).save(replyHeartDAOArgumentCaptor.capture());

        // Additional assertions based on the captured argument
        ReplyHeartDAO capturedReplyHeartDAO = replyHeartDAOArgumentCaptor.getValue();
        assertThat(capturedReplyHeartDAO.getReplyHeartId()).isEqualTo(replyHeartDAO.getReplyHeartId());
    }
}