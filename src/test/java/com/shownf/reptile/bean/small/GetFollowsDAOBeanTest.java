package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetFollowsDAOBeanTest {

    @Mock
    private FollowRepositoryJPA followRepositoryJPA;

    @InjectMocks
    private GetFollowsDAOBean getFollowsDAOBean;

    @Test
    void exec_withUserId_shouldReturnFollowDAOsForFollowUserId() {
        // Given
        Long userId = 1L;
        List<FollowDAO> expectedFollowDAOs = new ArrayList<>();
        expectedFollowDAOs.add(new FollowDAO());
        expectedFollowDAOs.add(new FollowDAO());

        // Mock the behavior of FollowRepositoryJPA
        when(followRepositoryJPA.findByFollowUserId(userId)).thenReturn(expectedFollowDAOs);

        // When
        List<FollowDAO> result = getFollowsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull().hasSize(2);

        // Verify that the findByFollowUserId method of FollowRepositoryJPA was called with the correct argument
        verify(followRepositoryJPA, times(1)).findByFollowUserId(userId);
    }

    @Test
    void exec_withUserIdAndCheck_shouldReturnFollowDAOsForUserId() {
        // Given
        Long userId = 1L;
        String check = "following";
        List<FollowDAO> expectedFollowDAOs = new ArrayList<>();
        expectedFollowDAOs.add(new FollowDAO());
        expectedFollowDAOs.add(new FollowDAO());

        // Mock the behavior of FollowRepositoryJPA
        when(followRepositoryJPA.findByUserId(userId)).thenReturn(expectedFollowDAOs);

        // When
        List<FollowDAO> result = getFollowsDAOBean.exec(userId, check);

        // Then
        assertThat(result).isNotNull().hasSize(2);

        // Verify that the findByUserId method of FollowRepositoryJPA was called with the correct argument
        verify(followRepositoryJPA, times(1)).findByUserId(userId);
    }
}