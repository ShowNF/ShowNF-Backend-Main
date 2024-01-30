package com.shownf.reptile.bean.small;

import org.junit.jupiter.api.Test;

import com.shownf.reptile.Model.entity.ImageDAO;
import com.shownf.reptile.Model.entity.ImageHeartDAO;
import com.shownf.reptile.repository.ImageRepositoryJPA;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdateImageHeartCountDAOBeanTest {

    @Mock
    private ImageRepositoryJPA imageRepositoryJPA;

    @InjectMocks
    private UpdateImageHeartCountDAOBean updateImageHeartCountDAOBean;

    @Test
    void testIncrementImageHeartCount() {
        // 가짜 데이터 설정
        ImageHeartDAO imageHeartDAO = new ImageHeartDAO();
        imageHeartDAO.setImageId(1L);

        ImageDAO imageDAO = new ImageDAO();
        imageDAO.setImageId(1L);
        imageDAO.setHeartCount(10);

        // Mock 설정
        when(imageRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(imageDAO));

        // 테스트 대상 메소드 호출
        ImageDAO updatedImageDAO = updateImageHeartCountDAOBean.exec(imageHeartDAO);

        // 검증
        assertEquals(11, updatedImageDAO.getHeartCount());
    }

    @Test
    void testDecrementImageHeartCount() {
        // 가짜 데이터 설정
        ImageHeartDAO imageHeartDAO = new ImageHeartDAO();
        imageHeartDAO.setImageId(1L);

        ImageDAO imageDAO = new ImageDAO();
        imageDAO.setImageId(1L);
        imageDAO.setHeartCount(10);

        // Mock 설정
        when(imageRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(imageDAO));

        // 테스트 대상 메소드 호출
        ImageDAO updatedImageDAO = updateImageHeartCountDAOBean.exec(1L, imageHeartDAO);

        // 검증
        assertEquals(9, updatedImageDAO.getHeartCount());
    }
}
