package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteFollowDAOBeanTest {

    @Mock
    private FollowRepositoryJPA followRepositoryJPA;

    @InjectMocks
    private DeleteFollowDAOBean deleteFollowDAOBean;

    @Test
    public void testDeleteFollowDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 FollowDAO 생성
        FollowDAO followDAO = new FollowDAO();

        // 테스트 대상 메서드 호출
        deleteFollowDAOBean.exec(followDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(followRepositoryJPA, times(1)).delete(followDAO);
    }
}
