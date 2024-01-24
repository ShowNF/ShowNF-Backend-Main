package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostHeartSaveDTO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.repository.PostHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostHeartDAOBeanTest {

    @Mock
    private PostHeartRepositoryJPA postHeartRepositoryJPA;

    @InjectMocks
    private GetPostHeartDAOBean getPostHeartDAOBean;

    @Test
    void exec_withExistingPostHeartId_shouldReturnPostHeartDAO() {
        // Given
        Long postHeartId = 1L;
        PostHeartDAO expectedPostHeartDAO = new PostHeartDAO();
        expectedPostHeartDAO.setPostHeartId(postHeartId);
        expectedPostHeartDAO.setUserId(1L);
        expectedPostHeartDAO.setPostId(1L);

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findById(postHeartId)).thenReturn(Optional.of(expectedPostHeartDAO));

        // When
        PostHeartDAO result = getPostHeartDAOBean.exec(postHeartId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPostHeartId()).isEqualTo(postHeartId);
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getPostId()).isEqualTo(1L);

        // Verify that the findById method of PostHeartRepositoryJPA was called with the correct argument
        verify(postHeartRepositoryJPA, times(1)).findById(postHeartId);
    }

    @Test
    void exec_withNonExistingPostHeartId_shouldReturnNull() {
        // Given
        Long nonExistingPostHeartId = 2L;

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findById(nonExistingPostHeartId)).thenReturn(Optional.empty());

        // When
        PostHeartDAO result = getPostHeartDAOBean.exec(nonExistingPostHeartId);

        // Then
        assertThat(result).isNull();

        // Verify that the findById method of PostHeartRepositoryJPA was called with the correct argument
        verify(postHeartRepositoryJPA, times(1)).findById(nonExistingPostHeartId);
    }

    @Test
    void exec_withExistingRequestPostHeartSaveDTO_shouldReturnPostHeartDAO() {
        // Given
        RequestPostHeartSaveDTO requestPostHeartSaveDTO = new RequestPostHeartSaveDTO();
        requestPostHeartSaveDTO.setPostId(1L);
        requestPostHeartSaveDTO.setUserId(2L);

        PostHeartDAO expectedPostHeartDAO = new PostHeartDAO();
        expectedPostHeartDAO.setPostHeartId(1L);
        expectedPostHeartDAO.setUserId(1L);
        expectedPostHeartDAO.setPostId(2L);

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findByUserIdAndPostId(requestPostHeartSaveDTO.getUserId(), requestPostHeartSaveDTO.getPostId()))
                .thenReturn(expectedPostHeartDAO);

        // When
        PostHeartDAO result = getPostHeartDAOBean.exec(requestPostHeartSaveDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPostHeartId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getPostId()).isEqualTo(2L);

        // Verify that the findByUserIdAndPostId method of PostHeartRepositoryJPA was called with the correct arguments
        verify(postHeartRepositoryJPA, times(1)).findByUserIdAndPostId(requestPostHeartSaveDTO.getUserId(), requestPostHeartSaveDTO.getPostId());
    }

    @Test
    void exec_withNonExistingRequestPostHeartSaveDTO_shouldReturnNull() {
        // Given
        RequestPostHeartSaveDTO nonExistingRequestPostHeartSaveDTO = new RequestPostHeartSaveDTO();
        nonExistingRequestPostHeartSaveDTO.setPostId(1L);
        nonExistingRequestPostHeartSaveDTO.setUserId(2L);

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findByUserIdAndPostId(nonExistingRequestPostHeartSaveDTO.getUserId(), nonExistingRequestPostHeartSaveDTO.getPostId()))
                .thenReturn(null);

        // When
        PostHeartDAO result = getPostHeartDAOBean.exec(nonExistingRequestPostHeartSaveDTO);

        // Then
        assertThat(result).isNull();

        // Verify that the findByUserIdAndPostId method of PostHeartRepositoryJPA was called with the correct arguments
        verify(postHeartRepositoryJPA, times(1)).findByUserIdAndPostId(nonExistingRequestPostHeartSaveDTO.getUserId(), nonExistingRequestPostHeartSaveDTO.getPostId());
    }

    @Test
    void exec_withExistingRequestPostHeartDeleteDTO_shouldReturnPostHeartDAO() {
        // Given
        RequestPostHeartDeleteDTO requestPostHeartDeleteDTO = new RequestPostHeartDeleteDTO();
        requestPostHeartDeleteDTO.setPostId(1L);
        requestPostHeartDeleteDTO.setUserId(2L);

        PostHeartDAO expectedPostHeartDAO = new PostHeartDAO();
        expectedPostHeartDAO.setPostHeartId(1L);
        expectedPostHeartDAO.setUserId(1L);
        expectedPostHeartDAO.setPostId(2L);

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findByUserIdAndPostId(requestPostHeartDeleteDTO.getUserId(), requestPostHeartDeleteDTO.getPostId()))
                .thenReturn(expectedPostHeartDAO);

        // When
        PostHeartDAO result = getPostHeartDAOBean.exec(requestPostHeartDeleteDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPostHeartId()).isEqualTo(1L);
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getPostId()).isEqualTo(2L);

        // Verify that the findByUserIdAndPostId method of PostHeartRepositoryJPA was called with the correct arguments
        verify(postHeartRepositoryJPA, times(1)).findByUserIdAndPostId(requestPostHeartDeleteDTO.getUserId(), requestPostHeartDeleteDTO.getPostId());
    }

    @Test
    void exec_withNonExistingRequestPostHeartDeleteDTO_shouldReturnNull() {
        // Given
        RequestPostHeartDeleteDTO nonExistingRequestPostHeartDeleteDTO = new RequestPostHeartDeleteDTO();
        nonExistingRequestPostHeartDeleteDTO.setPostId(1L);
        nonExistingRequestPostHeartDeleteDTO.setUserId(2L);

        // Mock the behavior of PostHeartRepositoryJPA
        when(postHeartRepositoryJPA.findByUserIdAndPostId(nonExistingRequestPostHeartDeleteDTO.getUserId(), nonExistingRequestPostHeartDeleteDTO.getPostId()))
                .thenReturn(null);

        // When
        PostHeartDAO result = getPostHeartDAOBean.exec(nonExistingRequestPostHeartDeleteDTO);

        // Then
        assertThat(result).isNull();

        // Verify that the findByUserIdAndPostId method of PostHeartRepositoryJPA was called with the correct arguments
        verify(postHeartRepositoryJPA, times(1)).findByUserIdAndPostId(nonExistingRequestPostHeartDeleteDTO.getUserId(), nonExistingRequestPostHeartDeleteDTO.getPostId());
    }
}
