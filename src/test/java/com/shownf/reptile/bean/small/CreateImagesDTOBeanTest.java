package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestImageDTO;
import com.shownf.reptile.Model.DTO.ResponseImagesDTO;
import com.shownf.reptile.Model.entity.ImageDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateImagesDTOBeanTest {

    @Autowired
    CreateImagesDTOBean createImagesDTOBean;

    @Test
    void execPage() {
        // 테스트할 데이터 생성
        List<ImageDAO> imageDAOs = new ArrayList<>();
        ImageDAO imageDAO1 = new ImageDAO(1L, "image1.jpg", "https://example.com/image1.jpg", LocalDateTime.now(), 10);
        ImageDAO imageDAO2 = new ImageDAO(2L, "image2.jpg", "https://example.com/image2.jpg", LocalDateTime.now(), 5);
        imageDAOs.add(imageDAO1);
        imageDAOs.add(imageDAO2);

        // 테스트 실행
        Pageable pageable = PageRequest.of(0, 10);
        Page<RequestImageDTO> resultPage = createImagesDTOBean.exec(pageable, new PageImpl<>(imageDAOs));

        // 결과 검증
        assertThat(resultPage.getContent()).hasSize(2);

        RequestImageDTO resultDTO1 = resultPage.getContent().get(0);
        assertThat(resultDTO1.getImageId()).isEqualTo(imageDAO1.getImageId());
        assertThat(resultDTO1.getImageName()).isEqualTo(imageDAO1.getImageName());
        assertThat(resultDTO1.getImageUrl()).isEqualTo(imageDAO1.getImageUrl());
        assertThat(resultDTO1.getUploadTime()).isEqualTo(imageDAO1.getUploadTime());
        assertThat(resultDTO1.getHeartCount()).isEqualTo(imageDAO1.getHeartCount());

        RequestImageDTO resultDTO2 = resultPage.getContent().get(1);
        assertThat(resultDTO2.getImageId()).isEqualTo(imageDAO2.getImageId());
        assertThat(resultDTO2.getImageName()).isEqualTo(imageDAO2.getImageName());
        assertThat(resultDTO2.getImageUrl()).isEqualTo(imageDAO2.getImageUrl());
        assertThat(resultDTO2.getUploadTime()).isEqualTo(imageDAO2.getUploadTime());
        assertThat(resultDTO2.getHeartCount()).isEqualTo(imageDAO2.getHeartCount());
    }

    @Test
    void execList() {
        // 테스트할 데이터 생성
        List<ImageDAO> imageDAOs = new ArrayList<>();
        ImageDAO imageDAO1 = new ImageDAO(1L, "image1.jpg", "https://example.com/image1.jpg", LocalDateTime.now(), 10);
        ImageDAO imageDAO2 = new ImageDAO(2L, "image2.jpg", "https://example.com/image2.jpg", LocalDateTime.now(), 5);
        imageDAOs.add(imageDAO1);
        imageDAOs.add(imageDAO2);

        // 테스트 실행
        List<ResponseImagesDTO> resultResponseImagesDTOs = createImagesDTOBean.exec(imageDAOs);

        // 결과 검증
        assertThat(resultResponseImagesDTOs).hasSize(2);

        ResponseImagesDTO resultDTO1 = resultResponseImagesDTOs.get(0);
        assertThat(resultDTO1.getImageId()).isEqualTo(imageDAO1.getImageId());
        assertThat(resultDTO1.getImageName()).isEqualTo(imageDAO1.getImageName());
        assertThat(resultDTO1.getImageUrl()).isEqualTo(imageDAO1.getImageUrl());
        assertThat(resultDTO1.getUploadTime()).isEqualTo(imageDAO1.getUploadTime());
        assertThat(resultDTO1.getHeartCount()).isEqualTo(imageDAO1.getHeartCount());

        ResponseImagesDTO resultDTO2 = resultResponseImagesDTOs.get(1);
        assertThat(resultDTO2.getImageId()).isEqualTo(imageDAO2.getImageId());
        assertThat(resultDTO2.getImageName()).isEqualTo(imageDAO2.getImageName());
        assertThat(resultDTO2.getImageUrl()).isEqualTo(imageDAO2.getImageUrl());
        assertThat(resultDTO2.getUploadTime()).isEqualTo(imageDAO2.getUploadTime());
        assertThat(resultDTO2.getHeartCount()).isEqualTo(imageDAO2.getHeartCount());
    }
}