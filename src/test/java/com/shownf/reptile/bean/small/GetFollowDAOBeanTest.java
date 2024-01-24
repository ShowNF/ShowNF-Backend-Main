package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetFollowDAOBeanTest {

    @Mock
    private FollowRepositoryJPA followRepositoryJPA;

    @InjectMocks
    private GetFollowDAOBean getFollowDAOBean;

    @Test
    void exec_withRequestFollowDTO_shouldReturnFollowDAOIfExists() {
        // Given
        RequestFollowDTO requestFollowDTO = new RequestFollowDTO();
        requestFollowDTO.setUserId(1L);
        requestFollowDTO.setFollowUserId(2L);

        FollowDAO expectedFollowDAO = new FollowDAO();
        expectedFollowDAO.setFollowId(1L);
        expectedFollowDAO.setUserId(requestFollowDTO.getUserId());
        expectedFollowDAO.setFollowUserId(requestFollowDTO.getFollowUserId());

        // Mock the behavior of FollowRepositoryJPA
        when(followRepositoryJPA.findByUserIdAndFollowUserId(requestFollowDTO.getUserId(), requestFollowDTO.getFollowUserId()))
                .thenReturn(expectedFollowDAO);

        // When
        FollowDAO result = getFollowDAOBean.exec(requestFollowDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getFollowId()).isEqualTo(expectedFollowDAO.getFollowId());
        assertThat(result.getUserId()).isEqualTo(expectedFollowDAO.getUserId());
        assertThat(result.getFollowUserId()).isEqualTo(expectedFollowDAO.getFollowUserId());

        // Verify that the findByUserIdAndFollowUserId method of FollowRepositoryJPA was called with the correct arguments
        verify(followRepositoryJPA, times(1))
                .findByUserIdAndFollowUserId(requestFollowDTO.getUserId(), requestFollowDTO.getFollowUserId());
    }

    @Test
    void exec_withNonExistingFollow_shouldReturnNull() {
        // Given
        RequestFollowDTO nonExistingFollowDTO = new RequestFollowDTO();
        nonExistingFollowDTO.setUserId(1L);
        nonExistingFollowDTO.setFollowUserId(3L);

        // Mock the behavior of FollowRepositoryJPA
        when(followRepositoryJPA.findByUserIdAndFollowUserId(nonExistingFollowDTO.getUserId(), nonExistingFollowDTO.getFollowUserId()))
                .thenReturn(null);

        // When
        FollowDAO result = getFollowDAOBean.exec(nonExistingFollowDTO);

        // Then
        assertThat(result).isNull();

        // Verify that the findByUserIdAndFollowUserId method of FollowRepositoryJPA was called with the correct arguments
        verify(followRepositoryJPA, times(1))
                .findByUserIdAndFollowUserId(nonExistingFollowDTO.getUserId(), nonExistingFollowDTO.getFollowUserId());
    }
}