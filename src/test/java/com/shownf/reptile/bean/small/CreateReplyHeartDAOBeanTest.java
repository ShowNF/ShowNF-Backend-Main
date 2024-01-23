package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyHeartSaveDTO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest
class CreateReplyHeartDAOBeanTest {

    @Autowired
    private CreateReplyHeartDAOBean createReplyHeartDAOBean;

    @Test
    void exec_shouldCreateReplyHeartDAOWithGivenInput() {
        // Given
        Long replyHeartId = 1L;
        Long replyId = 2L;
        Long userId = 3L;
        LocalDateTime uploadTime = LocalDateTime.now();

        RequestReplyHeartSaveDTO requestReplyHeartSaveDTO = new RequestReplyHeartSaveDTO();
        requestReplyHeartSaveDTO.setReplyId(replyId);
        requestReplyHeartSaveDTO.setUserId(userId);

        // When
        ReplyHeartDAO result = createReplyHeartDAOBean.exec(replyHeartId, requestReplyHeartSaveDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getReplyHeartId()).isEqualTo(replyHeartId);
        assertThat(result.getReplyId()).isEqualTo(replyId);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getUploadTime()).isCloseTo(uploadTime, within(1, ChronoUnit.SECONDS));
    }
}