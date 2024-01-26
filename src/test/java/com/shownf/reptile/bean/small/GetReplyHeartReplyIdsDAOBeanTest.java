package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetReplyHeartReplyIdsDAOBeanTest {

    @Autowired
    GetReplyHeartReplyIdsDAOBean getReplyHeartReplyIdsDAOBean;

    @Test
    void exec_withValidReplyHeartDAOList_shouldReturnReplyIds() {
        // Given
        List<ReplyHeartDAO> replyHeartDAOS = new ArrayList<>();
        replyHeartDAOS.add(new ReplyHeartDAO(1L, 1L, 101L, null));
        replyHeartDAOS.add(new ReplyHeartDAO(2L, 2L, 102L, null));

        // When
        List<Long> result = getReplyHeartReplyIdsDAOBean.exec(replyHeartDAOS);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(1L, 2L);
    }

    @Test
    void exec_withEmptyReplyHeartDAOList_shouldReturnEmptyList() {
        // Given
        List<ReplyHeartDAO> replyHeartDAOS = new ArrayList<>();

        // When
        List<Long> result = getReplyHeartReplyIdsDAOBean.exec(replyHeartDAOS);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}