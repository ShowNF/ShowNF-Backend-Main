package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplyHeartSaveDTO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.ReplyHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetReplyHeartDAOBeanTest {

    private final ReplyHeartRepositoryJPA replyHeartRepositoryJPA = mock(ReplyHeartRepositoryJPA.class);
    private final GetReplyHeartDAOBean getReplyHeartDAOBean = new GetReplyHeartDAOBean(replyHeartRepositoryJPA);

    @Test
    void exec_withValidReplyHeartId_shouldReturnReplyHeartDAO() {
        // Given
        Long replyHeartId = 1L;
        ReplyHeartDAO expectedReplyHeartDAO = new ReplyHeartDAO();
        expectedReplyHeartDAO.setReplyHeartId(replyHeartId);
        expectedReplyHeartDAO.setReplyId(1L);
        expectedReplyHeartDAO.setUserId(1L);

        // Mock the behavior of ReplyHeartRepositoryJPA
        when(replyHeartRepositoryJPA.findById(replyHeartId)).thenReturn(Optional.of(expectedReplyHeartDAO));

        // When
        ReplyHeartDAO result = getReplyHeartDAOBean.exec(replyHeartId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedReplyHeartDAO);

        // Verify that the findById method of ReplyHeartRepositoryJPA was called with the correct argument
        verify(replyHeartRepositoryJPA, times(1)).findById(replyHeartId);
    }

    @Test
    void exec_withInvalidReplyHeartId_shouldReturnNull() {
        // Given
        Long replyHeartId = 2L;

        // Mock the behavior of ReplyHeartRepositoryJPA
        when(replyHeartRepositoryJPA.findById(replyHeartId)).thenReturn(Optional.empty());

        // When
        ReplyHeartDAO result = getReplyHeartDAOBean.exec(replyHeartId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of ReplyHeartRepositoryJPA was called with the correct argument
        verify(replyHeartRepositoryJPA, times(1)).findById(replyHeartId);
    }

    @Test
    void exec_withRequestReplyHeartSaveDTO_shouldReturnReplyHeartDAO() {
        // Given
        RequestReplyHeartSaveDTO requestReplyHeartSaveDTO = new RequestReplyHeartSaveDTO();
        requestReplyHeartSaveDTO.setReplyId(1L);
        requestReplyHeartSaveDTO.setUserId(1L);

        ReplyHeartDAO expectedReplyHeartDAO = new ReplyHeartDAO();
        expectedReplyHeartDAO.setReplyHeartId(1L);
        expectedReplyHeartDAO.setReplyId(1L);
        expectedReplyHeartDAO.setUserId(1L);

        // Mock the behavior of ReplyHeartRepositoryJPA
        when(replyHeartRepositoryJPA.findByUserIdAndReplyId(requestReplyHeartSaveDTO.getUserId(), requestReplyHeartSaveDTO.getReplyId()))
                .thenReturn(expectedReplyHeartDAO);

        // When
        ReplyHeartDAO result = getReplyHeartDAOBean.exec(requestReplyHeartSaveDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedReplyHeartDAO);

        // Verify that the findByUserIdAndReplyId method of ReplyHeartRepositoryJPA was called with the correct arguments
        verify(replyHeartRepositoryJPA, times(1))
                .findByUserIdAndReplyId(requestReplyHeartSaveDTO.getUserId(), requestReplyHeartSaveDTO.getReplyId());
    }

    @Test
    void exec_withRequestReplyHeartDeleteDTO_shouldReturnReplyHeartDAO() {
        // Given
        RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO = new RequestReplyHeartDeleteDTO();
        requestReplyHeartDeleteDTO.setReplyId(1L);
        requestReplyHeartDeleteDTO.setUserId(1L);

        ReplyHeartDAO expectedReplyHeartDAO = new ReplyHeartDAO();
        expectedReplyHeartDAO.setReplyHeartId(1L);
        expectedReplyHeartDAO.setReplyId(1L);
        expectedReplyHeartDAO.setUserId(1L);

        // Mock the behavior of ReplyHeartRepositoryJPA
        when(replyHeartRepositoryJPA.findByUserIdAndReplyId(requestReplyHeartDeleteDTO.getUserId(), requestReplyHeartDeleteDTO.getReplyId()))
                .thenReturn(expectedReplyHeartDAO);

        // When
        ReplyHeartDAO result = getReplyHeartDAOBean.exec(requestReplyHeartDeleteDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedReplyHeartDAO);

        // Verify that the findByUserIdAndReplyId method of ReplyHeartRepositoryJPA was called with the correct arguments
        verify(replyHeartRepositoryJPA, times(1))
                .findByUserIdAndReplyId(requestReplyHeartDeleteDTO.getUserId(), requestReplyHeartDeleteDTO.getReplyId());
    }
}