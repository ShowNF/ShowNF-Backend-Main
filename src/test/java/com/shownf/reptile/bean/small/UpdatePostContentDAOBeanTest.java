package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostContentUpdateDTO;
import com.shownf.reptile.Model.entity.PostContentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdatePostContentDAOBeanTest {

    @Autowired
    private UpdatePostContentDAOBean updatePostContentDAOBean;

    @Test
    void testUpdatePostContent() {
        // 가짜 데이터 설정
        RequestPostContentUpdateDTO requestPostContentUpdateDTO = new RequestPostContentUpdateDTO();
        requestPostContentUpdateDTO.setContent("Updated Content");
        requestPostContentUpdateDTO.setImageUrl("updatedImageUrl");

        PostContentDAO postContentDAO = new PostContentDAO();
        postContentDAO.setContent("Original Content");
        postContentDAO.setImageUrl("originalImageUrl");

        // 테스트 대상 메소드 호출
        PostContentDAO updatedPostContentDAO = updatePostContentDAOBean.exec(requestPostContentUpdateDTO, postContentDAO);

        // 검증
        assertEquals("Updated Content", updatedPostContentDAO.getContent());
        assertEquals("updatedImageUrl", updatedPostContentDAO.getImageUrl());
    }

}