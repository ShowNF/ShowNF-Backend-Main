package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageHeartDAO;
import com.shownf.reptile.repository.ImageHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetImageHeartsDAOBeanTest {

    @Mock
    private ImageHeartRepositoryJPA imageHeartRepositoryJPA;

    @InjectMocks
    private GetImageHeartsDAOBean getImageHeartsDAOBean;

    @Test
    public void testGetImageHeartsDAO() {
        // 테스트 데이터 생성
        Long userId = 1L;
        ImageHeartDAO imageHeart1 = new ImageHeartDAO();
        ImageHeartDAO imageHeart2 = new ImageHeartDAO();
        List<ImageHeartDAO> expectedImageHearts = Arrays.asList(imageHeart1, imageHeart2);

        // imageHeartRepositoryJPA.findByUserId() 메서드가 호출될 때 테스트 데이터 반환하도록 설정
        when(imageHeartRepositoryJPA.findByUserId(userId)).thenReturn(expectedImageHearts);

        // GetImageHeartsDAOBean의 exec 메서드 호출
        List<ImageHeartDAO> resultImageHearts = getImageHeartsDAOBean.exec(userId);

        // 예상 결과와 실제 결과 비교
        assertEquals(expectedImageHearts, resultImageHearts);
    }
}
