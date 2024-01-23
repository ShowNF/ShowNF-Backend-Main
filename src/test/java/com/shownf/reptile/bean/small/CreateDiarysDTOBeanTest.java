package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseDiarysDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateDiarysDTOBeanTest {

    @Autowired
    CreateDiarysDTOBean createDiarysDTOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        List<DiaryDAO> diaryDAOs = new ArrayList<>();
        DiaryDAO diaryDAO1 = new DiaryDAO(1L, 100L, "image1.jpg", "Meal 1", 2, "Large", 10.5, "Memo 1",
                null, null, "2024 1 1", "202401", false, false, true, false, false);
        DiaryDAO diaryDAO2 = new DiaryDAO(2L, 100L, "image2.jpg", "Meal 2", 1, "Medium", 8.0, "Memo 2",
                null, null, "2024 1 1", "202401", false, true, false, true, false);
        diaryDAOs.add(diaryDAO1);
        diaryDAOs.add(diaryDAO2);

        // 테스트 실행
        List<ResponseDiarysDTO> resultResponseDiarysDTOs = createDiarysDTOBean.exec(diaryDAOs);

        // 결과 검증
        assertThat(resultResponseDiarysDTOs).hasSize(2);

        ResponseDiarysDTO resultDTO1 = resultResponseDiarysDTOs.get(0);
        assertThat(resultDTO1.getDiaryId()).isEqualTo(diaryDAO1.getDiaryId());
        assertThat(resultDTO1.getPetId()).isEqualTo(diaryDAO1.getPetId());
        assertThat(resultDTO1.getDate()).isEqualTo(diaryDAO1.getDate());

        ResponseDiarysDTO resultDTO2 = resultResponseDiarysDTOs.get(1);
        assertThat(resultDTO2.getDiaryId()).isEqualTo(diaryDAO2.getDiaryId());
        assertThat(resultDTO2.getPetId()).isEqualTo(diaryDAO2.getPetId());
        assertThat(resultDTO2.getDate()).isEqualTo(diaryDAO2.getDate());
    }
}