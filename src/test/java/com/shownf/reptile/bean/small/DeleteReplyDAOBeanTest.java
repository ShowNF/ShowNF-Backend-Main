package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.repository.ReplyRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteReplyDAOBeanTest {

    @Mock
    private ReplyRepositoryJPA replyRepositoryJPA;

    @InjectMocks
    private DeleteReplyDAOBean deleteReplyDAOBean;

    @Test
    public void testDeleteReplyDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 ReplyDAO 생성
        ReplyDAO replyDAO = new ReplyDAO();

        // 테스트 대상 메서드 호출
        deleteReplyDAOBean.exec(replyDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(replyRepositoryJPA, times(1)).delete(replyDAO);
    }
}
