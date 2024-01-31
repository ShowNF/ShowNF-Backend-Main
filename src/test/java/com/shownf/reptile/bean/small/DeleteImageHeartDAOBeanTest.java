package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageHeartDAO;
import com.shownf.reptile.repository.ImageHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteImageHeartDAOBeanTest {

    @Mock
    private ImageHeartRepositoryJPA imageHeartRepositoryJPA;

    @InjectMocks
    private DeleteImageHeartDAOBean deleteImageHeartDAOBean;

    @Test
    public void testDeleteImageHeartDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 ImageHeartDAO 생성
        ImageHeartDAO imageHeartDAO = new ImageHeartDAO();

        // 테스트 대상 메서드 호출
        deleteImageHeartDAOBean.exec(imageHeartDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(imageHeartRepositoryJPA, times(1)).delete(imageHeartDAO);
    }
}
