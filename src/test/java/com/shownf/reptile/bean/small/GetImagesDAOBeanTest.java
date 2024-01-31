package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageDAO;
import com.shownf.reptile.repository.ImageRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetImagesDAOBeanTest {

    @Mock
    private ImageRepositoryJPA imageRepositoryJPA;

    @InjectMocks
    private GetImagesDAOBean getImagesDAOBean;

    @Test
    public void testGetAllImages() {
        // Mock 데이터 생성
        Page<ImageDAO> mockPage = mock(Page.class);
        when(imageRepositoryJPA.findAll(any(Pageable.class))).thenReturn(mockPage);

        // 테스트 대상 메서드 호출
        Page<ImageDAO> resultPage = getImagesDAOBean.exec(Pageable.unpaged());

        // Mock 메서드 호출 검증
        verify(imageRepositoryJPA, times(1)).findAll(any(Pageable.class));

        // 결과 검증
        assertEquals(mockPage, resultPage);
    }

    @Test
    public void testGetImagesByIds() {
        // Mock 데이터 생성
        ImageDAO image1 = new ImageDAO();
        image1.setImageId(1L);
        ImageDAO image2 = new ImageDAO();
        image2.setImageId(2L);
        List<Long> imageIds = Arrays.asList(image1.getImageId(), image2.getImageId());

        when(imageRepositoryJPA.findById(image1.getImageId())).thenReturn(java.util.Optional.of(image1));
        when(imageRepositoryJPA.findById(image2.getImageId())).thenReturn(java.util.Optional.of(image2));

        // 테스트 대상 메서드 호출
        List<ImageDAO> resultImages = getImagesDAOBean.exec(imageIds);

        // Mock 메서드 호출 검증
        verify(imageRepositoryJPA, times(1)).findById(image1.getImageId());
        verify(imageRepositoryJPA, times(1)).findById(image2.getImageId());

        // 결과 검증
        List<ImageDAO> expectedImages = Arrays.asList(image1, image2);
        assertEquals(expectedImages, resultImages);
    }
}
