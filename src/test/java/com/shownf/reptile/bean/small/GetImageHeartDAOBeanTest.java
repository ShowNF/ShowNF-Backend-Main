package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageHeartDAO;
import com.shownf.reptile.repository.ImageHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetImageHeartDAOBeanTest {

    @Mock
    private ImageHeartRepositoryJPA imageHeartRepositoryJPA;

    @InjectMocks
    private GetImageHeartDAOBean getImageHeartDAOBean;

    @Test
    public void testGetImageHeartDAO() {
        // 테스트 데이터 생성
        Long imageHeartId = 1L;
        ImageHeartDAO expectedImageHeartDAO = new ImageHeartDAO();

        // imageHeartRepositoryJPA.findById() 메서드가 호출될 때 테스트 데이터 반환하도록 설정
        when(imageHeartRepositoryJPA.findById(imageHeartId)).thenReturn(Optional.of(expectedImageHeartDAO));

        // GetImageHeartDAOBean의 exec 메서드 호출
        ImageHeartDAO resultImageHeartDAO = getImageHeartDAOBean.exec(imageHeartId);

        // 예상 결과와 실제 결과 비교
        assertEquals(expectedImageHeartDAO, resultImageHeartDAO);
    }
}
