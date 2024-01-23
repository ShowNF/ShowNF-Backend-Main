package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostContentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreatePostMetaDTOBeanTest {

    @Autowired
    CreatePostMetaDTOBean createPostMetaDTOBean;

    @MockBean
    GetPostContentDAOBean getPostContentDAOBean;

    @Test
    void exec() throws IOException {
        // 테스트할 데이터 생성
        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(1L);
        postMeta.setUserId(101L);
        postMeta.setTitle("Test Post");
        postMeta.setContent("1");
        postMeta.setCategory(Category.TURTLE);
        postMeta.setUploadTime(LocalDateTime.of(2022, 1, 1, 12, 0));
        postMeta.setUpdateTime(LocalDateTime.of(2022, 1, 2, 10, 30));
        postMeta.setHeartCount(15);
        postMeta.setCommentCount(7);
        postMeta.setViewCount(120);

        PostContentDAO postContentDAO = new PostContentDAO();
        postContentDAO.setImageUrl("test_image_url");
        postContentDAO.setContent("test_content");

        // getPostContentDAOBean의 mock 설정
        when(getPostContentDAOBean.exec(1L)).thenReturn(postContentDAO);

        // 테스트 실행
        ResponsePostMetaDTO resultDTO = createPostMetaDTOBean.exec(postMeta);

        // 결과 검증
        assertThat(resultDTO.getPostId()).isEqualTo(1L);
        assertThat(resultDTO.getUserId()).isEqualTo(101L);
        assertThat(resultDTO.getTitle()).isEqualTo("Test Post");
        assertThat(resultDTO.getContent()).isEqualTo("[{\"imageUrl\":\"test_image_url\",\"content\":\"test_content\"}]");
        assertThat(resultDTO.getCategory()).isEqualTo("TURTLE");
        assertThat(resultDTO.getUploadTime()).isEqualTo(LocalDateTime.of(2022, 1, 1, 12, 0));
        assertThat(resultDTO.getUpdateTime()).isEqualTo(LocalDateTime.of(2022, 1, 2, 10, 30));
        assertThat(resultDTO.getHeartCount()).isEqualTo(15);
        assertThat(resultDTO.getCommentCount()).isEqualTo(7);
        assertThat(resultDTO.getViewCount()).isEqualTo(120);

    }

    @Test
    void execList() throws IOException {
        // 테스트할 데이터 생성
        List<PostMeta> postMetas = new ArrayList<>();
        // 테스트할 데이터 생성
        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(1L);
        postMeta.setUserId(101L);
        postMeta.setTitle("Test Post");
        postMeta.setContent("1");
        postMeta.setCategory(Category.TURTLE);
        postMeta.setUploadTime(LocalDateTime.of(2022, 1, 1, 12, 0));
        postMeta.setUpdateTime(LocalDateTime.of(2022, 1, 2, 10, 30));
        postMeta.setHeartCount(15);
        postMeta.setCommentCount(7);
        postMeta.setViewCount(120);

        // 테스트할 데이터 생성
        PostMeta postMeta2 = new PostMeta();
        postMeta2.setPostId(2L);
        postMeta2.setUserId(102L);
        postMeta2.setTitle("Test Post 2");
        postMeta2.setContent("2");
        postMeta2.setCategory(Category.TURTLE);
        postMeta2.setUploadTime(LocalDateTime.of(2022, 1, 1, 12, 0));
        postMeta2.setUpdateTime(LocalDateTime.of(2022, 1, 2, 10, 30));
        postMeta2.setHeartCount(15);
        postMeta2.setCommentCount(7);
        postMeta2.setViewCount(120);

        PostContentDAO postContentDAO = new PostContentDAO();
        postContentDAO.setImageUrl("test_image_url");
        postContentDAO.setContent("test_content");

        // getPostContentDAOBean의 mock 설정
        when(getPostContentDAOBean.exec(1L)).thenReturn(postContentDAO);

        PostContentDAO postContentDAO2 = new PostContentDAO();
        postContentDAO2.setImageUrl("test_image_url");
        postContentDAO2.setContent("test_content");

        // getPostContentDAOBean의 mock 설정
        when(getPostContentDAOBean.exec(2L)).thenReturn(postContentDAO2);


        postMetas.add(postMeta);
        postMetas.add(postMeta2);

        // 테스트 실행
        List<ResponsePostMetaDTO> resultDTOList = createPostMetaDTOBean.exec(postMetas);

        // 결과 검증
        assertThat(resultDTOList).hasSize(2);

        assertThat(resultDTOList.get(0).getPostId()).isEqualTo(1L);
        assertThat(resultDTOList.get(0).getUserId()).isEqualTo(101L);
        assertThat(resultDTOList.get(0).getTitle()).isEqualTo("Test Post");
        assertThat(resultDTOList.get(0).getContent()).isEqualTo("[{\"imageUrl\":\"test_image_url\",\"content\":\"test_content\"}]");
        assertThat(resultDTOList.get(0).getCategory()).isEqualTo("TURTLE");
        assertThat(resultDTOList.get(0).getUploadTime()).isEqualTo(LocalDateTime.of(2022, 1, 1, 12, 0));
        assertThat(resultDTOList.get(0).getUpdateTime()).isEqualTo(LocalDateTime.of(2022, 1, 2, 10, 30));
        assertThat(resultDTOList.get(0).getHeartCount()).isEqualTo(15);
        assertThat(resultDTOList.get(0).getCommentCount()).isEqualTo(7);
        assertThat(resultDTOList.get(0).getViewCount()).isEqualTo(120);

        assertThat(resultDTOList.get(1).getPostId()).isEqualTo(2L);
        assertThat(resultDTOList.get(1).getUserId()).isEqualTo(102L);
        assertThat(resultDTOList.get(1).getTitle()).isEqualTo("Test Post 2");
        assertThat(resultDTOList.get(1).getContent()).isEqualTo("[{\"imageUrl\":\"test_image_url\",\"content\":\"test_content\"}]");
        assertThat(resultDTOList.get(1).getCategory()).isEqualTo("TURTLE");
        assertThat(resultDTOList.get(1).getUploadTime()).isEqualTo(LocalDateTime.of(2022, 1, 1, 12, 0));
        assertThat(resultDTOList.get(1).getUpdateTime()).isEqualTo(LocalDateTime.of(2022, 1, 2, 10, 30));
        assertThat(resultDTOList.get(1).getHeartCount()).isEqualTo(15);
        assertThat(resultDTOList.get(1).getCommentCount()).isEqualTo(7);
        assertThat(resultDTOList.get(1).getViewCount()).isEqualTo(120);
    }
}