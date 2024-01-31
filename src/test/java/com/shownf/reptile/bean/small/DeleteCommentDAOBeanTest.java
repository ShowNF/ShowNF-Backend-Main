package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteCommentDAOBeanTest {

    @Mock
    private CommentRepositoryJPA commentRepositoryJPA;

    @InjectMocks
    private DeleteCommentDAOBean deleteCommentDAOBean;

    @Test
    public void testDeleteCommentDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 CommentDAO 생성
        CommentDAO commentDAO = new CommentDAO();

        // 테스트 대상 메서드 호출
        deleteCommentDAOBean.exec(commentDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(commentRepositoryJPA, times(1)).delete(commentDAO);
    }
}
