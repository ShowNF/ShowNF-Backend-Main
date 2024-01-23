package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.repository.CommentHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetCommentHeartDAOBeanTest {

    @Mock
    private CommentHeartRepositoryJPA commentHeartRepositoryJPA;

    @InjectMocks
    private GetCommentHeartDAOBean getCommentHeartDAOBean;

    @Test
    void exec_withCommentHeartId_shouldReturnCommentHeartDAOIfExists() {
        // Given
        Long commentHeartId = 1L;
        CommentHeartDAO expectedCommentHeartDAO = new CommentHeartDAO();
        expectedCommentHeartDAO.setCommentHeartId(commentHeartId);

        // Mock the behavior of CommentHeartRepositoryJPA
        when(commentHeartRepositoryJPA.findById(commentHeartId)).thenReturn(Optional.of(expectedCommentHeartDAO));

        // When
        CommentHeartDAO result = getCommentHeartDAOBean.exec(commentHeartId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getCommentHeartId()).isEqualTo(commentHeartId);

        // Verify that the findById method of CommentHeartRepositoryJPA was called with the correct argument
        verify(commentHeartRepositoryJPA, times(1)).findById(commentHeartId);
    }

    @Test
    void exec_withRequestCommentHeartSaveDTO_shouldReturnCommentHeartDAOIfExists() {
        // Given
        RequestCommentHeartSaveDTO requestCommentHeartSaveDTO = new RequestCommentHeartSaveDTO();
        requestCommentHeartSaveDTO.setUserId(1L);
        requestCommentHeartSaveDTO.setCommentId(2L);

        CommentHeartDAO expectedCommentHeartDAO = new CommentHeartDAO();
        expectedCommentHeartDAO.setUserId(requestCommentHeartSaveDTO.getUserId());
        expectedCommentHeartDAO.setCommentId(requestCommentHeartSaveDTO.getCommentId());

        // Mock the behavior of CommentHeartRepositoryJPA
        when(commentHeartRepositoryJPA.findByUserIdAndCommentId(requestCommentHeartSaveDTO.getUserId(), requestCommentHeartSaveDTO.getCommentId()))
                .thenReturn(expectedCommentHeartDAO);

        // When
        CommentHeartDAO result = getCommentHeartDAOBean.exec(requestCommentHeartSaveDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(requestCommentHeartSaveDTO.getUserId());
        assertThat(result.getCommentId()).isEqualTo(requestCommentHeartSaveDTO.getCommentId());

        // Verify that the findByUserIdAndCommentId method of CommentHeartRepositoryJPA was called with the correct arguments
        verify(commentHeartRepositoryJPA, times(1))
                .findByUserIdAndCommentId(requestCommentHeartSaveDTO.getUserId(), requestCommentHeartSaveDTO.getCommentId());
    }

    @Test
    void exec_withRequestCommentHeartDeleteDTO_shouldReturnCommentHeartDAOIfExists() {
        // Given
        RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO = new RequestCommentHeartDeleteDTO();
        requestCommentHeartDeleteDTO.setUserId(1L);
        requestCommentHeartDeleteDTO.setCommentId(2L);

        CommentHeartDAO expectedCommentHeartDAO = new CommentHeartDAO();
        expectedCommentHeartDAO.setUserId(requestCommentHeartDeleteDTO.getUserId());
        expectedCommentHeartDAO.setCommentId(requestCommentHeartDeleteDTO.getCommentId());

        // Mock the behavior of CommentHeartRepositoryJPA
        when(commentHeartRepositoryJPA.findByUserIdAndCommentId(requestCommentHeartDeleteDTO.getUserId(), requestCommentHeartDeleteDTO.getCommentId()))
                .thenReturn(expectedCommentHeartDAO);

        // When
        CommentHeartDAO result = getCommentHeartDAOBean.exec(requestCommentHeartDeleteDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(requestCommentHeartDeleteDTO.getUserId());
        assertThat(result.getCommentId()).isEqualTo(requestCommentHeartDeleteDTO.getCommentId());

        // Verify that the findByUserIdAndCommentId method of CommentHeartRepositoryJPA was called with the correct arguments
        verify(commentHeartRepositoryJPA, times(1))
                .findByUserIdAndCommentId(requestCommentHeartDeleteDTO.getUserId(), requestCommentHeartDeleteDTO.getCommentId());
    }
}