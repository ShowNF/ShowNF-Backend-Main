package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.Enum.Level;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreatePetsDTOBeanTest {

    @Autowired
    CreatePetsDTOBean createPetsDTOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        List<PetDAO> petDAOs = new ArrayList<>();
        PetDAO petDAO1 = new PetDAO();
        petDAO1.setPetId(1L);
        petDAO1.setUserId(101L);
        petDAO1.setImageUrl("https://example.com/pet1.jpg");
        petDAO1.setName("Fluffy");
        petDAO1.setFirstSpecies("Dog");
        petDAO1.setSecondSpecies("Poodle");
        petDAO1.setBirthday("20010213");
        petDAO1.setWeight(5.5);
        petDAO1.setGender(Gender.MALE);
        petDAO1.setUploadTime(LocalDateTime.now());
        petDAO1.setUpdateTime(LocalDateTime.now());
        petDAO1.setDiaryCount(10);
        petDAO1.setLevel(Level.LEVEL_5);
        petDAO1.setLevelExperience(600);
        petDAO1.setDeleteCheck(false);

        PetDAO petDAO2 = new PetDAO();
        petDAO2.setPetId(2L);
        petDAO2.setUserId(101L);
        petDAO2.setImageUrl("https://example.com/pet2.jpg");
        petDAO2.setName("Mittens");
        petDAO2.setFirstSpecies("Cat");
        petDAO2.setSecondSpecies("Siamese");
        petDAO2.setBirthday("20010214");
        petDAO2.setWeight(4.2);
        petDAO2.setGender(Gender.FEMALE);
        petDAO2.setUploadTime(LocalDateTime.now());
        petDAO2.setUpdateTime(LocalDateTime.now());
        petDAO2.setDiaryCount(8);
        petDAO2.setLevel(Level.LEVEL_4);
        petDAO2.setLevelExperience(500);
        petDAO2.setDeleteCheck(false);

        petDAOs.add(petDAO1);
        petDAOs.add(petDAO2);

        // 테스트 실행
        Page<ResponsePetDTO> resultPage = createPetsDTOBean.exec(true, PageRequest.of(0, 10), new PageImpl<>(petDAOs));

        // 결과 검증
        assertThat(resultPage.getContent()).hasSize(2);

        ResponsePetDTO resultDTO1 = resultPage.getContent().get(0);
        assertThat(resultDTO1.getPetId()).isEqualTo(1L);
        assertThat(resultDTO1.getUserId()).isEqualTo(101L);
        assertThat(resultDTO1.getImageUrl()).isEqualTo("https://example.com/pet1.jpg");
        assertThat(resultDTO1.getName()).isEqualTo("Fluffy");
        assertThat(resultDTO1.getFirstSpecies()).isEqualTo("Dog");
        assertThat(resultDTO1.getSecondSpecies()).isEqualTo("Poodle");
        assertThat(resultDTO1.getBirthday()).isEqualTo("20010213");
        assertThat(resultDTO1.getWeight()).isEqualTo(5.5);
        assertThat(resultDTO1.getGender()).isEqualTo("MALE");
        assertThat(resultDTO1.getUploadTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(resultDTO1.getUpdateTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(resultDTO1.getDiaryCount()).isEqualTo(10);
        assertThat(resultDTO1.getLevel()).isEqualTo(Level.LEVEL_5);
        assertThat(resultDTO1.getLevelExperience()).isEqualTo(600);

        ResponsePetDTO resultDTO2 = resultPage.getContent().get(1);
        assertThat(resultDTO2.getPetId()).isEqualTo(2L);
        assertThat(resultDTO2.getUserId()).isEqualTo(101L);
        assertThat(resultDTO2.getImageUrl()).isEqualTo("https://example.com/pet2.jpg");
        assertThat(resultDTO2.getName()).isEqualTo("Mittens");
        assertThat(resultDTO2.getFirstSpecies()).isEqualTo("Cat");
        assertThat(resultDTO2.getSecondSpecies()).isEqualTo("Siamese");
        assertThat(resultDTO2.getBirthday()).isEqualTo("20010214");
        assertThat(resultDTO2.getWeight()).isEqualTo(4.2);
        assertThat(resultDTO2.getGender()).isEqualTo("FEMALE");
        assertThat(resultDTO2.getUploadTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(resultDTO2.getUpdateTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(resultDTO2.getDiaryCount()).isEqualTo(8);
        assertThat(resultDTO2.getLevel()).isEqualTo(Level.LEVEL_4);
        assertThat(resultDTO2.getLevelExperience()).isEqualTo(500);
    }
}