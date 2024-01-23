package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CreatePostsDTOBeanTest {

    @Autowired
    CreatePostsDTOBean createPostsDTOBean;

    @Test
    void exec() {
        // Mock 데이터 생성
        List<PostMeta> postMetaList = new ArrayList<>();
        postMetaList.add(createPostMeta(1L));
        postMetaList.add(createPostMeta(2L));

        // Pageable 객체 생성
        Pageable pageable = mock(Pageable.class);

        // CreatePostsDTOBean 객체 생성
        CreatePostsDTOBean createPostsDTOBean = new CreatePostsDTOBean();

        // 테스트 실행
        Page<Long> result = createPostsDTOBean.exec(pageable, new PageImpl<>(postMetaList, pageable, postMetaList.size()));

        // 결과 검증
        assertThat(result).isNotNull();
        assertThat(result.getContent()).containsExactly(1L, 2L); // 예상되는 postId 값들
        assertThat(result.getSize()).isEqualTo(postMetaList.size());
        assertThat(result.getTotalElements()).isEqualTo(postMetaList.size());
    }

    private PostMeta createPostMeta(Long postId) {
        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(postId);
        // 나머지 필드 값 설정
        return postMeta;
    }
}