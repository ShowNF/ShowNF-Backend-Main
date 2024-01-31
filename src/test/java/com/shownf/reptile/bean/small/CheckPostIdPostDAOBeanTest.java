package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostHeartDeleteDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckPostIdPostDAOBeanTest {

    @Autowired
    CheckPostIdPostDAOBean checkPostIdPostDAOBean;

    @Test
    public void testCheckPostIdForPostHeartDAO() {

        // 테스트에 사용할 더미 PostHeartDAO 및 RequestPostHeartDeleteDTO 생성
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setPostId(1L);

        RequestPostHeartDeleteDTO requestPostHeartDeleteDTO = new RequestPostHeartDeleteDTO();
        requestPostHeartDeleteDTO.setPostId(1L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertTrue(checkPostIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO));
    }

    @Test
    public void testCheckPostIdForPostHeartDAOMismatch() {

        // 테스트에 사용할 더미 PostHeartDAO 및 RequestPostHeartDeleteDTO 생성
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setPostId(1L);

        RequestPostHeartDeleteDTO requestPostHeartDeleteDTO = new RequestPostHeartDeleteDTO();
        requestPostHeartDeleteDTO.setPostId(2L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertFalse(checkPostIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO));
    }

    @Test
    public void testCheckPostIdForCommentDAO() {

        // 테스트에 사용할 더미 CommentDAO 및 RequestCommentDeleteDTO 생성
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setPostId(2L);

        RequestCommentDeleteDTO requestCommentDeleteDTO = new RequestCommentDeleteDTO();
        requestCommentDeleteDTO.setPostId(2L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertTrue(checkPostIdPostDAOBean.exec(commentDAO, requestCommentDeleteDTO));
    }

    @Test
    public void testCheckPostIdForCommentDAOMismatch() {

        // 테스트에 사용할 더미 PostHeartDAO 및 RequestPostHeartDeleteDTO 생성
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setPostId(1L);

        RequestPostHeartDeleteDTO requestPostHeartDeleteDTO = new RequestPostHeartDeleteDTO();
        requestPostHeartDeleteDTO.setPostId(2L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertFalse(checkPostIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO));
    }
}
