package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestImageHeartSaveDTO;
import com.shownf.reptile.Model.entity.ImageHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateImageHeartDAOBeanTest {

    @Autowired
    CreateImageHeartDAOBean createImageHeartDAOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        Long imageHeartId = 1L;
        RequestImageHeartSaveDTO requestImageHeartSaveDTO = new RequestImageHeartSaveDTO();
        requestImageHeartSaveDTO.setImageId(100L);
        requestImageHeartSaveDTO.setUserId(50L);

        // 테스트 실행
        ImageHeartDAO resultImageHeartDAO = createImageHeartDAOBean.exec(imageHeartId, requestImageHeartSaveDTO);

        // 현재 시간을 가져와 업로드 시간과 비교
        LocalDateTime currentTime = LocalDateTime.now();

        // 결과 검증
        assertThat(resultImageHeartDAO.getImageHeartId()).isEqualTo(imageHeartId);
        assertThat(resultImageHeartDAO.getImageId()).isEqualTo(requestImageHeartSaveDTO.getImageId());
        assertThat(resultImageHeartDAO.getUserId()).isEqualTo(requestImageHeartSaveDTO.getUserId());
        assertThat(resultImageHeartDAO.getUploadTime()).isBeforeOrEqualTo(currentTime);
    }
}