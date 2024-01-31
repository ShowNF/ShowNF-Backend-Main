package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.repository.CommentHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteCommentHeartDAOBeanTest {

    @Mock
    private CommentHeartRepositoryJPA commentHeartRepositoryJPA;

    @InjectMocks
    private DeleteCommentHeartDAOBean deleteCommentHeartDAOBean;

    @Test
    public void testDeleteCommentHeartDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 CommentHeartDAO 생성
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();

        // 테스트 대상 메서드 호출
        deleteCommentHeartDAOBean.exec(commentHeartDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(commentHeartRepositoryJPA, times(1)).delete(commentHeartDAO);
    }
}
