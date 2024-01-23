package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ImageDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateImageDAOBeanTest {

    @Autowired
    CreateImageDAOBean createImageDAOBean;

    @Test
    void exec() throws IOException {
        // 테스트할 데이터 생성
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "Hello, World!".getBytes());
        String imageUrl = "https://example.com/image/test.jpg";

        // 테스트 실행
        ImageDAO resultImageDAO = createImageDAOBean.exec(file, imageUrl);

        // 현재 시간을 가져와 업로드 시간과 비교
        LocalDateTime currentTime = LocalDateTime.now();

        // 결과 검증
        assertThat(resultImageDAO.getImageId()).isNotNull();
        assertThat(resultImageDAO.getImageName()).isEqualTo("test.jpg");
        assertThat(resultImageDAO.getImageUrl()).isEqualTo(imageUrl);
        assertThat(resultImageDAO.getUploadTime()).isBeforeOrEqualTo(currentTime);
        assertThat(resultImageDAO.getHeartCount()).isEqualTo(0);
    }
}