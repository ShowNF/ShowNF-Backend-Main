package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.repository.PostHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeletePostHeartDAOBeanTest {

    @Mock
    private PostHeartRepositoryJPA postHeartRepositoryJPA;

    @InjectMocks
    private DeletePostHeartDAOBean deletePostHeartDAOBean;

    @Test
    public void testDeletePostHeartDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 PostHeartDAO 생성
        PostHeartDAO postHeartDAO = new PostHeartDAO();

        // 테스트 대상 메서드 호출
        deletePostHeartDAOBean.exec(postHeartDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(postHeartRepositoryJPA, times(1)).delete(postHeartDAO);
    }
}
