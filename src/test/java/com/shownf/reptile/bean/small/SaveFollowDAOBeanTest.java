package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@SpringBootTest
class SaveFollowDAOBeanTest {

    @Mock
    FollowRepositoryJPA followRepositoryJPA;

    @InjectMocks
    SaveFollowDAOBean saveFollowDAOBean;

    @Test
    void exec_shouldSaveFollow() {
        // Given
        FollowDAO followDAO = new FollowDAO(1L, 2L, 3L, LocalDateTime.now());

        // When
        saveFollowDAOBean.exec(followDAO);

        // Then
        // Verify that the save method is called with the expected FollowDAO
        verify(followRepositoryJPA, times(1)).save(followDAO);
    }

}