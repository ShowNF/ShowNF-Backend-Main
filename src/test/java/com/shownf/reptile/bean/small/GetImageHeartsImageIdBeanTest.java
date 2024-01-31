package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetImageHeartsImageIdBeanTest {

    @Autowired
    GetImageHeartsImageIdBean getImageHeartsImageIdBean;

    @Test
    public void testGetImageHeartsImageIdBean() {
        // 테스트 데이터 생성
        ImageHeartDAO imageHeart1 = new ImageHeartDAO();
        imageHeart1.setImageId(1L);
        ImageHeartDAO imageHeart2 = new ImageHeartDAO();
        imageHeart2.setImageId(2L);

        List<ImageHeartDAO> imageHeartDAOs = Arrays.asList(imageHeart1, imageHeart2);

        // GetImageHeartsImageIdBean의 exec 메서드 호출
        List<Long> resultImageIds = getImageHeartsImageIdBean.exec(imageHeartDAOs);

        // 예상 결과와 실제 결과 비교
        List<Long> expectedImageIds = Arrays.asList(1L, 2L);
        assertEquals(expectedImageIds, resultImageIds);
    }
}
