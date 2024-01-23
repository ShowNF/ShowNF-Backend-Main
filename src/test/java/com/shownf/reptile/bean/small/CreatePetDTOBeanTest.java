package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.Enum.Level;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreatePetDTOBeanTest {

    @Autowired
    CreatePetDTOBean createPetDTOBean;

    @Test
    void exec() {
        // 테스트할 데이터 생성
        PetDAO petDAO = new PetDAO();
        petDAO.setPetId(1L);
        petDAO.setUserId(101L);
        petDAO.setImageUrl("https://example.com/pet.jpg");
        petDAO.setName("Fluffy");
        petDAO.setFirstSpecies("Dog");
        petDAO.setSecondSpecies("Poodle");
        petDAO.setBirthday("20010213");
        petDAO.setWeight(5.5);
        petDAO.setGender(Gender.MALE);
        petDAO.setUploadTime(LocalDateTime.now());
        petDAO.setUpdateTime(LocalDateTime.now());
        petDAO.setDiaryCount(10);
        petDAO.setLevel(Level.LEVEL_5);
        petDAO.setLevelExperience(600);

        // 테스트 실행
        ResponsePetDTO resultResponsePetDTO = createPetDTOBean.exec(petDAO);

        // 결과 검증
        assertThat(resultResponsePetDTO.getPetId()).isEqualTo(1L);
        assertThat(resultResponsePetDTO.getUserId()).isEqualTo(101L);
        assertThat(resultResponsePetDTO.getImageUrl()).isEqualTo("https://example.com/pet.jpg");
        assertThat(resultResponsePetDTO.getName()).isEqualTo("Fluffy");
        assertThat(resultResponsePetDTO.getFirstSpecies()).isEqualTo("Dog");
        assertThat(resultResponsePetDTO.getSecondSpecies()).isEqualTo("Poodle");
        assertThat(resultResponsePetDTO.getBirthday()).isEqualTo("20010213");
        assertThat(resultResponsePetDTO.getWeight()).isEqualTo(5.5);
        assertThat(resultResponsePetDTO.getGender()).isEqualTo("MALE");
        assertThat(resultResponsePetDTO.getUploadTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(resultResponsePetDTO.getUpdateTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(resultResponsePetDTO.getDiaryCount()).isEqualTo(10);
        assertThat(resultResponsePetDTO.getLevel()).isEqualTo(Level.LEVEL_5);
        assertThat(resultResponsePetDTO.getLevelExperience()).isEqualTo(600);
    }
}