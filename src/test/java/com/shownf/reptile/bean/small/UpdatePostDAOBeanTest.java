package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostUpdateDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdatePostDAOBeanTest {

    @Autowired
    private UpdatePostDAOBean updatePostDAOBean;

    @Test
    void testUpdatePostDAO() {
        // 가짜 데이터 설정
        List<Map<Integer, Long>> updateContent = Arrays.asList(
                Collections.singletonMap(0, 100L),
                Collections.singletonMap(1, 200L)
        );

        RequestPostUpdateDTO requestPostUpdateDTO = new RequestPostUpdateDTO();
        requestPostUpdateDTO.setCategory("TURTLE");
        requestPostUpdateDTO.setTitle("Updated Title");

        PostDAO postDAO = new PostDAO();
        postDAO.setContent("[{\"0\":1},{\"1\":2}]");
        postDAO.setUpdateTime(LocalDateTime.of(2022, 1, 1, 0, 0));

        // 테스트 대상 메소드 호출
        PostDAO updatedPostDAO = updatePostDAOBean.exec(updateContent, requestPostUpdateDTO, postDAO);

        // 검증
        assertEquals(Category.TURTLE, updatedPostDAO.getCategory());
        assertEquals("Updated Title", updatedPostDAO.getTitle());
        assertEquals("[{\"0\":100},{\"1\":200}]", updatedPostDAO.getContent());
        assertEquals(LocalDateTime.now().getYear(), updatedPostDAO.getUpdateTime().getYear());
    }

    @Test
    void testUpdatePostMeta() {
        // 가짜 데이터 설정
        List<Map<Integer, Long>> updateContent = Arrays.asList(
                Collections.singletonMap(0, 100L),
                Collections.singletonMap(1, 200L)
        );

        RequestPostUpdateDTO requestPostUpdateDTO = new RequestPostUpdateDTO();
        requestPostUpdateDTO.setCategory("TURTLE");
        requestPostUpdateDTO.setTitle("Updated Title");

        PostMeta postMeta = new PostMeta();
        postMeta.setContent("0");
        postMeta.setUpdateTime(LocalDateTime.of(2022, 1, 1, 0, 0));

        // 테스트 대상 메소드 호출
        PostMeta updatedPostMeta = updatePostDAOBean.exec(updateContent, requestPostUpdateDTO, postMeta);

        // 검증
        assertEquals(Category.TURTLE, updatedPostMeta.getCategory());
        assertEquals("Updated Title", updatedPostMeta.getTitle());
        assertEquals("100", updatedPostMeta.getContent());
        assertEquals(LocalDateTime.now().getYear(), updatedPostMeta.getUpdateTime().getYear());
    }
}
