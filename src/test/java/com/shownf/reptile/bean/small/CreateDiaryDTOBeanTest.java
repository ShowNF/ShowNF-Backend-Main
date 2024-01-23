package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseDiaryDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateDiaryDTOBeanTest {

    @Autowired
    CreateDiaryDTOBean createDiaryDTOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        DiaryDAO diaryDAO = new DiaryDAO();
        diaryDAO.setDiaryId(1L);
        diaryDAO.setPetId(100L);
        diaryDAO.setImageUrl("image-url.jpg");
        diaryDAO.setFood("Meal");
        diaryDAO.setFoodCounter(2);
        diaryDAO.setSize("Large");
        diaryDAO.setWeight(10.5);
        diaryDAO.setMemo("Test diary memo");
        diaryDAO.setUploadTime(LocalDateTime.now());
        diaryDAO.setUpdateTime(LocalDateTime.now());
        diaryDAO.setDate("2024 1 1");
        diaryDAO.setMonth("202401");
        diaryDAO.setEcdysis(true);
        diaryDAO.setCleaning(false);
        diaryDAO.setShower(true);
        diaryDAO.setBowelMovement(false);

        // 테스트 실행
        ResponseDiaryDTO resultResponseDiaryDTO = createDiaryDTOBean.exec(diaryDAO);

        // 결과 검증
        assertThat(resultResponseDiaryDTO.getDiaryId()).isEqualTo(diaryDAO.getDiaryId());
        assertThat(resultResponseDiaryDTO.getPetId()).isEqualTo(diaryDAO.getPetId());
        assertThat(resultResponseDiaryDTO.getImageUrl()).isEqualTo(diaryDAO.getImageUrl());
        assertThat(resultResponseDiaryDTO.getFood()).isEqualTo(diaryDAO.getFood());
        assertThat(resultResponseDiaryDTO.getFoodCounter()).isEqualTo(diaryDAO.getFoodCounter());
        assertThat(resultResponseDiaryDTO.getSize()).isEqualTo(diaryDAO.getSize());
        assertThat(resultResponseDiaryDTO.getWeight()).isEqualTo(diaryDAO.getWeight());
        assertThat(resultResponseDiaryDTO.getMemo()).isEqualTo(diaryDAO.getMemo());
        assertThat(resultResponseDiaryDTO.getUploadTime()).isEqualTo(diaryDAO.getUploadTime());
        assertThat(resultResponseDiaryDTO.getUpdateTime()).isEqualTo(diaryDAO.getUpdateTime());
        assertThat(resultResponseDiaryDTO.getDate()).isEqualTo(diaryDAO.getDate());
        assertThat(resultResponseDiaryDTO.getMonth()).isEqualTo(diaryDAO.getMonth());
        assertThat(resultResponseDiaryDTO.isEcdysis()).isEqualTo(diaryDAO.isEcdysis());
        assertThat(resultResponseDiaryDTO.isCleaning()).isEqualTo(diaryDAO.isCleaning());
        assertThat(resultResponseDiaryDTO.isShower()).isEqualTo(diaryDAO.isShower());
        assertThat(resultResponseDiaryDTO.isBowelMovement()).isEqualTo(diaryDAO.isBowelMovement());
    }
}