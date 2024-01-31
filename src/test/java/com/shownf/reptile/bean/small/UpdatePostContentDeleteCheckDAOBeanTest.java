package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostContentDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UpdatePostContentDeleteCheckDAOBeanTest {

    @Autowired
    private UpdatePostContentDeleteCheckDAOBean updatePostContentDeleteCheckDAOBean;

    @Test
    void testUpdatePostContentDeleteCheck() {
        // 가짜 데이터 설정
        List<PostContentDAO> postContentDAOs = Arrays.asList(
                new PostContentDAO(),
                new PostContentDAO(),
                new PostContentDAO()
        );

        // 테스트 대상 메소드 호출
        List<PostContentDAO> updatedPostContentDAOs = updatePostContentDeleteCheckDAOBean.exec(postContentDAOs);

        // 검증
        assertTrue(updatedPostContentDAOs.stream().allMatch(PostContentDAO::isDeleteCheck));
    }
}