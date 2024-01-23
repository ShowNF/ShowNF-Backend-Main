package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseCommentsDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateCommentsDTOBeanTest {

    @Autowired
    CreateCommentsDTOBean createCommentsDTOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        List<CommentDAO> commentDAOs = new ArrayList<>();
        CommentDAO commentDAO1 = new CommentDAO(1L, 100L, 50L, "Comment 1", LocalDateTime.now(), LocalDateTime.now(), 0, 0, false);
        CommentDAO commentDAO2 = new CommentDAO(2L, 100L, 60L, "Comment 2", LocalDateTime.now(), LocalDateTime.now(), 0, 0, false);
        commentDAOs.add(commentDAO1);
        commentDAOs.add(commentDAO2);

        // 테스트 실행
        List<ResponseCommentsDTO> resultResponseCommentsDTOs = createCommentsDTOBean.exec(commentDAOs);

        // 결과 검증
        assertThat(resultResponseCommentsDTOs).hasSize(2);

        ResponseCommentsDTO resultDTO1 = resultResponseCommentsDTOs.get(0);
        assertThat(resultDTO1.getCommentId()).isEqualTo(commentDAO1.getCommentId());
        assertThat(resultDTO1.getPostId()).isEqualTo(commentDAO1.getPostId());
        assertThat(resultDTO1.getUserId()).isEqualTo(commentDAO1.getUserId());
        assertThat(resultDTO1.getContent()).isEqualTo(commentDAO1.getContent());
        assertThat(resultDTO1.getUploadTime()).isEqualTo(commentDAO1.getUploadTime());
        assertThat(resultDTO1.getUpdateTime()).isEqualTo(commentDAO1.getUpdateTime());
        assertThat(resultDTO1.getHeartCount()).isEqualTo(commentDAO1.getHeartCount());
        assertThat(resultDTO1.getReplyCount()).isEqualTo(commentDAO1.getReplyCount());

        ResponseCommentsDTO resultDTO2 = resultResponseCommentsDTOs.get(1);
        assertThat(resultDTO2.getCommentId()).isEqualTo(commentDAO2.getCommentId());
        assertThat(resultDTO2.getPostId()).isEqualTo(commentDAO2.getPostId());
        assertThat(resultDTO2.getUserId()).isEqualTo(commentDAO2.getUserId());
        assertThat(resultDTO2.getContent()).isEqualTo(commentDAO2.getContent());
        assertThat(resultDTO2.getUploadTime()).isEqualTo(commentDAO2.getUploadTime());
        assertThat(resultDTO2.getUpdateTime()).isEqualTo(commentDAO2.getUpdateTime());
        assertThat(resultDTO2.getHeartCount()).isEqualTo(commentDAO2.getHeartCount());
        assertThat(resultDTO2.getReplyCount()).isEqualTo(commentDAO2.getReplyCount());
    }
}