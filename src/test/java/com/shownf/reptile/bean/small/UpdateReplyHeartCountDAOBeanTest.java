package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateReplyHeartCountDAOBeanTest {

    @Mock
    private GetReplyDAOBean getReplyDAOBean;

    @InjectMocks
    private UpdateReplyHeartCountDAOBean updateReplyHeartCountDAOBean;

    public UpdateReplyHeartCountDAOBeanTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecAddHeartCount() {
        // 가짜 데이터 설정
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setReplyId(1L);

        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setHeartCount(10);

        // Mockito를 사용하여 getReplyDAOBean.exec(replyId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getReplyDAOBean.exec(1L)).thenReturn(replyDAO);

        // 테스트 대상 메소드 호출
        ReplyDAO updatedReplyDAO = updateReplyHeartCountDAOBean.exec(replyHeartDAO);

        // 검증
        assertEquals(11, updatedReplyDAO.getHeartCount());
    }

    @Test
    void testExecDecreaseHeartCount() {
        // 가짜 데이터 설정
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setReplyId(1L);

        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setHeartCount(10);

        // Mockito를 사용하여 getReplyDAOBean.exec(replyId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getReplyDAOBean.exec(1L)).thenReturn(replyDAO);

        // 테스트 대상 메소드 호출
        ReplyDAO updatedReplyDAO = updateReplyHeartCountDAOBean.exec(1L, replyHeartDAO);

        // 검증
        assertEquals(9, updatedReplyDAO.getHeartCount());
    }
}
