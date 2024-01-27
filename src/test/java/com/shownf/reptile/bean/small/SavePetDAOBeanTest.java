package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPetSaveDTO;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.Enum.Level;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.repository.PetRepositoryJPA;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SavePetDAOBeanTest {

    @Mock
    private PetRepositoryJPA petRepositoryJPA;

    @InjectMocks
    private SavePetDAOBean savePetDAOBean;

    @Test
    void exec_shouldSavePetToRepository() {
        // Given
        PetDAO petToSave = new PetDAO();
        petToSave.setPetId(1L);

        // When
        savePetDAOBean.exec(petToSave);

        // Then
        // Verify that the save method of petRepositoryJPA is called with the correct argument
        verify(petRepositoryJPA, times(1)).save(petToSave);
    }

    @Test
    void exec_shouldCreateAndSavePetDAO() throws IOException {
        // Given
        Long petId = 1L;
        RequestPetSaveDTO requestPetSaveDTO = new RequestPetSaveDTO();
        requestPetSaveDTO.setUserId(1L);

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", "https://example.com/image.jpg");
        list.add(map);

        requestPetSaveDTO.setImageUrl(list);
        requestPetSaveDTO.setName("MyPet");
        requestPetSaveDTO.setFirstSpecies("Dog");
        requestPetSaveDTO.setSecondSpecies("Golden Retriever");
        requestPetSaveDTO.setBirthday("2022 1 23");
        requestPetSaveDTO.setWeight(10.5);
        requestPetSaveDTO.setGender("MALE");

        // When
        PetDAO createdPetDAO = savePetDAOBean.exec(petId, requestPetSaveDTO);


        assertThat(createdPetDAO.getPetId()).isEqualTo(petId);
        assertThat(createdPetDAO.getUserId()).isEqualTo(requestPetSaveDTO.getUserId());
        assertThat(createdPetDAO.getImageUrl()).isEqualTo("[{\"imageUrl\":\"https://example.com/image.jpg\"}]");
        assertThat(createdPetDAO.getName()).isEqualTo(requestPetSaveDTO.getName());
        assertThat(createdPetDAO.getFirstSpecies()).isEqualTo(requestPetSaveDTO.getFirstSpecies());
        assertThat(createdPetDAO.getSecondSpecies()).isEqualTo(requestPetSaveDTO.getSecondSpecies());
        assertThat(createdPetDAO.getBirthday()).isEqualTo("20220123"); // Check the formatted date
        assertThat(createdPetDAO.getWeight()).isEqualTo(requestPetSaveDTO.getWeight());
        assertThat(createdPetDAO.getGender()).isEqualTo(Gender.MALE);
        assertThat(createdPetDAO.getUploadTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(createdPetDAO.getUpdateTime()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(createdPetDAO.getDiaryCount()).isEqualTo(0);
        assertThat(createdPetDAO.getLevel()).isEqualTo(Level.LEVEL_1);
        assertThat(createdPetDAO.getLevelExperience()).isEqualTo(0);
        assertThat(createdPetDAO.isDeleteCheck()).isFalse();
    }
}