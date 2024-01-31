package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckUserIdCommentDAOBeanTest {

    @Mock
    private RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO;

    @Mock
    private RequestReplyDeleteDTO requestReplyDeleteDTO;

    @InjectMocks
    private CheckUserIdCommentDAOBean checkUserIdCommentDAOBean;

    @Test
    public void testCheckUserIdForCommentHeartDAO() {
        MockitoAnnotations.initMocks(this);

        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setUserId(1L);

        when(requestCommentHeartDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdCommentDAOBean.exec(commentHeartDAO, requestCommentHeartDeleteDTO));

    }

    @Test
    public void testCheckUserIdForReplyDAO() {
        MockitoAnnotations.initMocks(this);

        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setUserId(1L);

        when(requestReplyDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO));
    }
}
