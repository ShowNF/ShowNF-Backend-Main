package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateCommentDAOBeanTest {

    @Autowired
    CreateCommentDAOBean createCommentDAOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        Long commentId = 1L;
        RequestCommentSaveDTO requestCommentSaveDTO = new RequestCommentSaveDTO();
        requestCommentSaveDTO.setPostId(100L);
        requestCommentSaveDTO.setUserId(50L);
        requestCommentSaveDTO.setContent("Test comment content");

        // 테스트 실행
        CommentDAO resultCommentDAO = createCommentDAOBean.exec(commentId, requestCommentSaveDTO);

        // 현재 시간을 가져와 업로드 시간 및 수정 시간과 비교
        LocalDateTime currentTime = LocalDateTime.now();

        // 결과 검증
        assertThat(resultCommentDAO.getCommentId()).isEqualTo(commentId);
        assertThat(resultCommentDAO.getPostId()).isEqualTo(requestCommentSaveDTO.getPostId());
        assertThat(resultCommentDAO.getUserId()).isEqualTo(requestCommentSaveDTO.getUserId());
        assertThat(resultCommentDAO.getContent()).isEqualTo(requestCommentSaveDTO.getContent());
        assertThat(resultCommentDAO.getUploadTime()).isBeforeOrEqualTo(currentTime);
        assertThat(resultCommentDAO.getUpdateTime()).isBeforeOrEqualTo(currentTime);
        assertThat(resultCommentDAO.getHeartCount()).isEqualTo(0);
        assertThat(resultCommentDAO.getReplyCount()).isEqualTo(0);
        assertThat(resultCommentDAO.isDeleteCheck()).isEqualTo(false);
    }
}