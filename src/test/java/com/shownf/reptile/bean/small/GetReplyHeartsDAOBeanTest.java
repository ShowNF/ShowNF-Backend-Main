package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.ReplyHeartRepositoryJPA;
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
class GetReplyHeartsDAOBeanTest {

    @Mock
    ReplyHeartRepositoryJPA replyHeartRepositoryJPA;

    @InjectMocks
    GetReplyHeartsDAOBean getReplyHeartsDAOBean;

    @Test
    void exec_withUserId_shouldReturnReplyHearts() {
        // Given
        Long userId = 1L;
        List<ReplyHeartDAO> expectedReplyHearts = new ArrayList<>();
        expectedReplyHearts.add(new ReplyHeartDAO(1L, userId, 101L, null));
        expectedReplyHearts.add(new ReplyHeartDAO(2L, userId, 102L, null));

        // Mock the behavior of ReplyHeartRepositoryJPA
        when(replyHeartRepositoryJPA.findByUserId(userId))
                .thenReturn(expectedReplyHearts);

        // When
        List<ReplyHeartDAO> result = getReplyHeartsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(expectedReplyHearts);
    }

    @Test
    void exec_withUserIdAndEmptyList_shouldReturnEmptyList() {
        // Given
        Long userId = 2L;

        // Mock the behavior of ReplyHeartRepositoryJPA
        when(replyHeartRepositoryJPA.findByUserId(userId))
                .thenReturn(new ArrayList<>());

        // When
        List<ReplyHeartDAO> result = getReplyHeartsDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}