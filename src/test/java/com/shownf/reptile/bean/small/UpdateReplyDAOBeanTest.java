package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyUpdateDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateReplyDAOBeanTest {

    @Autowired
    UpdateReplyDAOBean updateReplyDAOBean;

    @Test
    void testUpdateReplyDAOBean() {
        // 가짜 데이터 설정
        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setContent("Old Content");
        replyDAO.setUpdateTime(LocalDateTime.now().minusDays(1));

        RequestReplyUpdateDTO requestReplyUpdateDTO = new RequestReplyUpdateDTO();
        requestReplyUpdateDTO.setContent("New Content");

        // 테스트 대상 메소드 호출
        ReplyDAO updatedReplyDAO = updateReplyDAOBean.exec(replyDAO, requestReplyUpdateDTO);

        // 검증
        assertEquals("New Content", updatedReplyDAO.getContent());

        // 수정시간이 현재 시간 근처인지 검증 (현재 시간과 비교해 일정한 여유를 두어야 함)
        assertEquals(LocalDateTime.now().getDayOfMonth(), updatedReplyDAO.getUpdateTime().getDayOfMonth());
    }
}
