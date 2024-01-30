package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestDiaryDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPetDisclosureDTO;
import com.shownf.reptile.Model.DTO.RequestPetUpdateDTO;
import com.shownf.reptile.Model.Enum.Disclosure;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdatePetDAOBeanTest {

    @Mock
    private GetPetDAOBean getPetDAOBean;

    @Mock
    private CheckLevelPetDAOBean checkLevelPetDAOBean;

    @InjectMocks
    private UpdatePetDAOBean updatePetDAOBean;

    @Test
    void testUpdatePet() {
        // 가짜 데이터 설정
        PetDAO petDAO = new PetDAO();
        RequestPetUpdateDTO requestPetUpdateDTO = new RequestPetUpdateDTO();

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", "testImageUrl.png");

        list.add(map);

        requestPetUpdateDTO.setImageUrl(list);
        requestPetUpdateDTO.setName("FakePet");
        requestPetUpdateDTO.setFirstSpecies("Species1");
        requestPetUpdateDTO.setSecondSpecies("Species2");
        requestPetUpdateDTO.setBirthday("2022 1 30");
        requestPetUpdateDTO.setWeight(5.0);
        requestPetUpdateDTO.setGender("MALE");

        // 테스트 대상 메소드 호출
        PetDAO updatedPetDAO = updatePetDAOBean.exec(petDAO, requestPetUpdateDTO);

        // 검증
        assertEquals("[{\"imageUrl\":\"testImageUrl.png\"}]", updatedPetDAO.getImageUrl());
        assertEquals("FakePet", updatedPetDAO.getName());
        assertEquals("Species1", updatedPetDAO.getFirstSpecies());
        assertEquals("Species2", updatedPetDAO.getSecondSpecies());
        assertEquals("20220130", updatedPetDAO.getBirthday());
        assertEquals(5.0, updatedPetDAO.getWeight());
        assertEquals(Gender.MALE, updatedPetDAO.getGender());
        assertEquals(LocalDateTime.now().getDayOfMonth(), updatedPetDAO.getUpdateTime().getDayOfMonth());
    }

    @Test
    void testUpdatePetDisclosure() {
        // 가짜 데이터 설정
        PetDAO petDAO = new PetDAO();
        RequestPetDisclosureDTO requestPetDisclosureDTO = new RequestPetDisclosureDTO();
        requestPetDisclosureDTO.setDisclosure("나만보기");

        // 테스트 대상 메소드 호출
        PetDAO updatedPetDAO = updatePetDAOBean.exec(petDAO, requestPetDisclosureDTO);

        // 검증
        assertEquals(Disclosure.나만보기, updatedPetDAO.getDisclosure());
    }

    @Test
    void testUpdatePetFromDiaryAdd() {
        // 가짜 데이터 설정
        DiaryDAO diaryDAO = new DiaryDAO();
        diaryDAO.setPetId(1L);
        diaryDAO.setImageUrl("[{\"imageUrl\":\"testImageUrl.png\"}]");
        diaryDAO.setWeight(5.0);

        PetDAO petDAO = new PetDAO();
        petDAO.setPetId(1L);
        petDAO.setDiaryCount(10);
        petDAO.setLevelExperience(50);

        // Mock 설정
        when(getPetDAOBean.exec(anyLong())).thenReturn(petDAO);
        when(checkLevelPetDAOBean.exec(petDAO)).thenReturn(petDAO);

        // 테스트 대상 메소드 호출
        PetDAO updatedPetDAO = updatePetDAOBean.exec(diaryDAO);

        // 검증
        assertEquals(11, updatedPetDAO.getDiaryCount());
        assertEquals("[{\"imageUrl\":\"testImageUrl.png\"}]", updatedPetDAO.getImageUrl());
        assertEquals(5.0, updatedPetDAO.getWeight());
        assertEquals(60, updatedPetDAO.getLevelExperience());
    }

    @Test
    void testUpdatePetFromDiaryUpdate() {
        // 가짜 데이터 설정
        DiaryDAO diaryDAO = new DiaryDAO();
        diaryDAO.setPetId(1L);
        diaryDAO.setImageUrl("[{\"imageUrl\":\"testImageUrl.png\"}]");
        diaryDAO.setWeight(7.5);

        PetDAO petDAO = new PetDAO();
        petDAO.setDiaryCount(10);

        // Mock 설정
        when(getPetDAOBean.exec(anyLong())).thenReturn(petDAO);

        // 테스트 대상 메소드 호출
        PetDAO updatedPetDAO = updatePetDAOBean.exec(7L, diaryDAO);

        // 검증
        assertEquals("[{\"imageUrl\":\"testImageUrl.png\"}]", updatedPetDAO.getImageUrl());
        assertEquals(7.5, updatedPetDAO.getWeight());
        assertEquals(10, updatedPetDAO.getDiaryCount());
    }

    @Test
    void testUpdatePetFromDiaryDelete() {
        // 가짜 데이터 설정
        RequestDiaryDeleteDTO requestDiaryDeleteDTO = new RequestDiaryDeleteDTO();
        requestDiaryDeleteDTO.setPetId(1L);

        PetDAO petDAO = new PetDAO();
        petDAO.setDiaryCount(10);
        petDAO.setLevelExperience(50);

        // Mock 설정
        when(getPetDAOBean.exec(anyLong())).thenReturn(petDAO);
        when(checkLevelPetDAOBean.exec(petDAO)).thenReturn(petDAO);

        // 테스트 대상 메소드 호출
        PetDAO updatedPetDAO = updatePetDAOBean.exec(requestDiaryDeleteDTO);

        // 검증
        assertEquals(9, updatedPetDAO.getDiaryCount());
        assertEquals(40, updatedPetDAO.getLevelExperience());
    }
}
