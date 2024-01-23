package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateReplyDAOBeanTest {

    @Autowired
    private CreateReplyDAOBean createReplyDAOBean;

    @Test
    void exec() {
        // Given
        Long replyId = 1L;
        Long commentId = 2L;
        Long userId = 3L;
        String content = "Sample Reply Content";
        LocalDateTime uploadTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();

        RequestReplySaveDTO requestReplySaveDTO = new RequestReplySaveDTO();
        requestReplySaveDTO.setCommentId(commentId);
        requestReplySaveDTO.setUserId(userId);
        requestReplySaveDTO.setContent(content);

        // When
        ReplyDAO result = createReplyDAOBean.exec(replyId, requestReplySaveDTO);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getReplyId()).isEqualTo(replyId);
        assertThat(result.getCommentId()).isEqualTo(commentId);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getContent()).isEqualTo(content);
        assertThat(result.getUploadTime()).isEqualTo(uploadTime);
        assertThat(result.getUpdateTime()).isEqualTo(updateTime);
        assertThat(result.getHeartCount()).isEqualTo(0);
        assertThat(result.isDeleteCheck()).isFalse();
    }
}