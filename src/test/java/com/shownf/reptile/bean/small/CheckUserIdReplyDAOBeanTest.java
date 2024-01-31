package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyHeartDeleteDTO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckUserIdReplyDAOBeanTest {

    @Mock
    private RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO;

    @InjectMocks
    private CheckUserIdReplyDAOBean checkUserIdReplyDAOBean;

    @Test
    public void testCheckUserIdForReplyHeartDAO() {
        MockitoAnnotations.initMocks(this);

        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setUserId(1L);

        when(requestReplyHeartDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdReplyDAOBean.exec(replyHeartDAO, requestReplyHeartDeleteDTO));
    }
}
