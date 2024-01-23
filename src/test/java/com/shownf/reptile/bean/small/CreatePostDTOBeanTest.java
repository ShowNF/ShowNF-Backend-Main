package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.entity.PostDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreatePostDTOBeanTest {

    @InjectMocks
    CreatePostDTOBean createPostDTOBean;

    @Mock
    GetPostContentDAOsBean getPostContentDAOsBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        PostDAO postDAO = new PostDAO();
        postDAO.setPostId(1L);
        postDAO.setUserId(101L);
        postDAO.setTitle("Sample Post");
        postDAO.setContent("[{\"0\":1}]");
        postDAO.setCategory(Category.TURTLE);
        postDAO.setUploadTime(LocalDateTime.of(2022, 1, 1, 12, 0));
        postDAO.setUpdateTime(LocalDateTime.of(2022, 1, 2, 10, 30));
        postDAO.setHeartCount(15);
        postDAO.setCommentCount(7);
        postDAO.setViewCount(120);

        // Mock 객체를 통해 메소드 호출 가로채기
        when(getPostContentDAOsBean.exec(1L, "[{\"0\":1}]"))
                .thenReturn("Mocked content"); // 원하는 반환값으로 설정

        // 테스트 실행
        RequestPostDTO resultDTO = createPostDTOBean.exec(postDAO);

        // 결과 검증
        assertThat(resultDTO.getPostId()).isEqualTo(1L);
        assertThat(resultDTO.getUserId()).isEqualTo(101L);
        assertThat(resultDTO.getTitle()).isEqualTo("Sample Post");
        assertThat(resultDTO.getContent()).isEqualTo("Mocked content"); // Mocked content로 대체됨
        assertThat(resultDTO.getCategory()).isEqualTo("TURTLE");
        assertThat(resultDTO.getUploadTime()).isEqualTo(LocalDateTime.of(2022, 1, 1, 12, 0));
        assertThat(resultDTO.getUpdateTime()).isEqualTo(LocalDateTime.of(2022, 1, 2, 10, 30));
        assertThat(resultDTO.getHeartCount()).isEqualTo(15);
        assertThat(resultDTO.getCommentCount()).isEqualTo(7);
        assertThat(resultDTO.getViewCount()).isEqualTo(120);
    }
}