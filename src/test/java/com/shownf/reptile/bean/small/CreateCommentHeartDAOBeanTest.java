package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateCommentHeartDAOBeanTest {

    @Autowired
    CreateCommentHeartDAOBean createCommentHeartDAOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        Long commentHeartId = 1L;
        RequestCommentHeartSaveDTO requestCommentHeartSaveDTO = new RequestCommentHeartSaveDTO();
        requestCommentHeartSaveDTO.setCommentId(100L);
        requestCommentHeartSaveDTO.setUserId(50L);

        // 테스트 실행
        CommentHeartDAO resultCommentHeartDAO = createCommentHeartDAOBean.exec(commentHeartId, requestCommentHeartSaveDTO);

        // 현재 시간을 가져와 업로드 시간과 비교
        LocalDateTime currentTime = LocalDateTime.now();

        // 결과 검증
        assertThat(resultCommentHeartDAO.getCommentHeartId()).isEqualTo(commentHeartId);
        assertThat(resultCommentHeartDAO.getCommentId()).isEqualTo(requestCommentHeartSaveDTO.getCommentId());
        assertThat(resultCommentHeartDAO.getUserId()).isEqualTo(requestCommentHeartSaveDTO.getUserId());
        assertThat(resultCommentHeartDAO.getUploadTime()).isBeforeOrEqualTo(currentTime);
    }
}