package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostHeartDeleteDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckUserIdPostDAOBeanTest {

    @Mock
    private RequestPostHeartDeleteDTO requestPostHeartDeleteDTO;

    @Mock
    private RequestCommentDeleteDTO requestCommentDeleteDTO;

    @InjectMocks
    private CheckUserIdPostDAOBean checkUserIdPostDAOBean;

    @Test
    public void testCheckUserIdForPostHeartDAO() {
        MockitoAnnotations.initMocks(this);

        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setUserId(1L);

        when(requestPostHeartDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO));
    }

    @Test
    public void testCheckUserIdForCommentDAO() {
        MockitoAnnotations.initMocks(this);

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setUserId(1L);

        when(requestCommentDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdPostDAOBean.exec(commentDAO, requestCommentDeleteDTO));
    }
}
