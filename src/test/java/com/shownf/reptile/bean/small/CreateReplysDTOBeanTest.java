package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseReplysDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateReplysDTOBeanTest {

    @Autowired
    private CreateReplysDTOBean createReplysDTOBean;

    @Test
    void exec_shouldCreateReplysDTOListWithGivenInput() {
        // Given
        ReplyDAO replyDAO1 = new ReplyDAO();
        replyDAO1.setReplyId(1L);
        replyDAO1.setCommentId(2L);
        replyDAO1.setUserId(3L);
        replyDAO1.setContent("Sample Reply Content 1");
        replyDAO1.setUploadTime(LocalDateTime.now());
        replyDAO1.setUpdateTime(LocalDateTime.now());
        replyDAO1.setHeartCount(5);

        ReplyDAO replyDAO2 = new ReplyDAO();
        replyDAO2.setReplyId(2L);
        replyDAO2.setCommentId(2L);
        replyDAO2.setUserId(4L);
        replyDAO2.setContent("Sample Reply Content 2");
        replyDAO2.setUploadTime(LocalDateTime.now());
        replyDAO2.setUpdateTime(LocalDateTime.now());
        replyDAO2.setHeartCount(3);

        List<ReplyDAO> replyDAOList = Arrays.asList(replyDAO1, replyDAO2);

        // When
        List<ResponseReplysDTO> result = createReplysDTOBean.exec(replyDAOList);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);

        // Verify the first ReplyDAO in the list
        ResponseReplysDTO resultDTO1 = result.get(0);
        assertThat(resultDTO1.getReplyId()).isEqualTo(replyDAO1.getReplyId());
        assertThat(resultDTO1.getCommentId()).isEqualTo(replyDAO1.getCommentId());
        assertThat(resultDTO1.getUserId()).isEqualTo(replyDAO1.getUserId());
        assertThat(resultDTO1.getContent()).isEqualTo(replyDAO1.getContent());
        assertThat(resultDTO1.getUploadTime()).isEqualTo(replyDAO1.getUploadTime());
        assertThat(resultDTO1.getUpdateTime()).isEqualTo(replyDAO1.getUpdateTime());
        assertThat(resultDTO1.getHeartCount()).isEqualTo(replyDAO1.getHeartCount());

        // Verify the second ReplyDAO in the list
        ResponseReplysDTO resultDTO2 = result.get(1);
        assertThat(resultDTO2.getReplyId()).isEqualTo(replyDAO2.getReplyId());
        assertThat(resultDTO2.getCommentId()).isEqualTo(replyDAO2.getCommentId());
        assertThat(resultDTO2.getUserId()).isEqualTo(replyDAO2.getUserId());
        assertThat(resultDTO2.getContent()).isEqualTo(replyDAO2.getContent());
        assertThat(resultDTO2.getUploadTime()).isEqualTo(replyDAO2.getUploadTime());
        assertThat(resultDTO2.getUpdateTime()).isEqualTo(replyDAO2.getUpdateTime());
        assertThat(resultDTO2.getHeartCount()).isEqualTo(replyDAO2.getHeartCount());
    }
}