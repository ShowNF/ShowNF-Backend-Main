package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentUpdateDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UpdateCommentDAOBeanTest {

    @Mock
    private GetCommentDAOBean getCommentDAOBean;

    @InjectMocks
    private UpdateCommentDAOBean updateCommentDAOBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateComment() {
        // 가짜 데이터 설정
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setContent("Old Content");
        commentDAO.setUpdateTime(LocalDateTime.now());

        RequestCommentUpdateDTO requestCommentUpdateDTO = new RequestCommentUpdateDTO();
        requestCommentUpdateDTO.setContent("New Content");

        // 테스트 대상 메소드 호출
        CommentDAO updatedCommentDAO = updateCommentDAOBean.exec(commentDAO, requestCommentUpdateDTO);

        // 검증
        assertEquals(requestCommentUpdateDTO.getContent(), updatedCommentDAO.getContent());
        assertEquals(commentDAO.getUpdateTime(), updatedCommentDAO.getUpdateTime());
    }

    @Test
    void testIncrementHeartCount() {
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setCommentId(1L);

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setHeartCount(10);

        when(getCommentDAOBean.exec(anyLong())).thenReturn(commentDAO);

        CommentDAO updatedCommentDAO = updateCommentDAOBean.exec(commentHeartDAO);

        assertEquals(11, updatedCommentDAO.getHeartCount());
    }

    @Test
    void testDecrementHeartCount() {
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setCommentId(1L);

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setHeartCount(10);

        when(getCommentDAOBean.exec(anyLong())).thenReturn(commentDAO);

        CommentDAO updatedCommentDAO = updateCommentDAOBean.exec(1L, commentHeartDAO);

        assertEquals(9, updatedCommentDAO.getHeartCount());
    }
}
