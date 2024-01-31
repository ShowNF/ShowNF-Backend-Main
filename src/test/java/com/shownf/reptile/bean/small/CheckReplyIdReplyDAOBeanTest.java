package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyHeartDeleteDTO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckReplyIdReplyDAOBeanTest {

    @Test
    public void testCheckReplyId() {
        CheckReplyIdReplyDAOBean checkReplyIdReplyDAOBean = new CheckReplyIdReplyDAOBean();

        // 테스트에 사용할 더미 ReplyHeartDAO 및 RequestReplyHeartDeleteDTO 생성
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setReplyId(1L);

        RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO = new RequestReplyHeartDeleteDTO();
        requestReplyHeartDeleteDTO.setReplyId(1L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertTrue(checkReplyIdReplyDAOBean.exec(replyHeartDAO, requestReplyHeartDeleteDTO));
    }

    @Test
    public void testCheckReplyIdMismatch() {
        CheckReplyIdReplyDAOBean checkReplyIdReplyDAOBean = new CheckReplyIdReplyDAOBean();

        // 테스트에 사용할 더미 ReplyHeartDAO 및 RequestReplyHeartDeleteDTO 생성
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setReplyId(1L);

        RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO = new RequestReplyHeartDeleteDTO();
        requestReplyHeartDeleteDTO.setReplyId(2L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertFalse(checkReplyIdReplyDAOBean.exec(replyHeartDAO, requestReplyHeartDeleteDTO));
    }
}
