package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.ReplyHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteReplyHeartDAOBeanTest {

    @Mock
    private ReplyHeartRepositoryJPA replyHeartRepositoryJPA;

    @InjectMocks
    private DeleteReplyHeartDAOBean deleteReplyHeartDAOBean;

    @Test
    public void testDeleteReplyHeartDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 ReplyHeartDAO 생성
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();

        // 테스트 대상 메서드 호출
        deleteReplyHeartDAOBean.exec(replyHeartDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(replyHeartRepositoryJPA, times(1)).delete(replyHeartDAO);
    }
}
