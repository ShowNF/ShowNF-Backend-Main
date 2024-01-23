package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestImageHeartDeleteDTO;
import com.shownf.reptile.Model.entity.ImageHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CheckImageIdImageDAOBeanTest {

    @Autowired
    CheckImageIdImageDAOBean checkImageIdImageDAOBean;

    // 이미지 좋아요 iId 판별
    @Test
    void exec() {
        // 테스트할 데이터 생성
        ImageHeartDAO imageHeartDAO = new ImageHeartDAO();
        RequestImageHeartDeleteDTO requestImageHeartDeleteDTO = new RequestImageHeartDeleteDTO();
        imageHeartDAO.setImageId(1L);
        requestImageHeartDeleteDTO.setImageId(1L);

        // 테스트 실행
        boolean test = checkImageIdImageDAOBean.exec(imageHeartDAO, requestImageHeartDeleteDTO);

        // 결과 검증
        assertThat(test).isEqualTo(true);
    }

    @Test
    void exec_DifferentImageIds() {
        // 테스트할 데이터 생성
        ImageHeartDAO imageHeartDAO = new ImageHeartDAO();
        RequestImageHeartDeleteDTO requestImageHeartDeleteDTO = new RequestImageHeartDeleteDTO();
        imageHeartDAO.setImageId(1L);
        requestImageHeartDeleteDTO.setImageId(2L);

        // 테스트 실행
        boolean test = checkImageIdImageDAOBean.exec(imageHeartDAO, requestImageHeartDeleteDTO);

        // 결과 검증
        assertThat(test).isEqualTo(false);
    }
}