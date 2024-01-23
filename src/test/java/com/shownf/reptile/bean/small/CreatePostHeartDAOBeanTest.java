package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostHeartSaveDTO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreatePostHeartDAOBeanTest {

    @Autowired
    CreatePostHeartDAOBean createPostHeartDAOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        RequestPostHeartSaveDTO requestPostHeartSaveDTO = new RequestPostHeartSaveDTO();
        requestPostHeartSaveDTO.setPostId(1L);
        requestPostHeartSaveDTO.setUserId(101L);

        // 테스트 실행
        PostHeartDAO resultDAO = createPostHeartDAOBean.exec(1L, requestPostHeartSaveDTO);

        // 결과 검증
        assertThat(resultDAO.getPostHeartId()).isEqualTo(1L);
        assertThat(resultDAO.getPostId()).isEqualTo(1L);
        assertThat(resultDAO.getUserId()).isEqualTo(101L);
        assertThat(resultDAO.getUploadTime()).isBeforeOrEqualTo(LocalDateTime.now());
    }
}