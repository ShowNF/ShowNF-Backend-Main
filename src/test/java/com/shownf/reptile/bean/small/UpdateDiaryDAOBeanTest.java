package com.shownf.reptile.bean.small;
import com.shownf.reptile.Model.DTO.RequestDiaryUpdateDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UpdateDiaryDAOBeanTest {

    @Autowired
    private UpdateDiaryDAOBean updateDiaryDAOBean;

    @Test
    void testUpdateDiary() {
        // 가짜 데이터 설정
        DiaryDAO diaryDAO = new DiaryDAO();
        RequestDiaryUpdateDTO requestDiaryUpdateDTO = new RequestDiaryUpdateDTO();

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("imageUrl", "testImageUrl.png");

        list.add(map);

        requestDiaryUpdateDTO.setImageUrl(list);
        requestDiaryUpdateDTO.setFood("fakeFood");
        requestDiaryUpdateDTO.setFoodCounter(3);
        requestDiaryUpdateDTO.setSize("fakeSize");
        requestDiaryUpdateDTO.setWeight(5.0);
        requestDiaryUpdateDTO.setMemo("fakeMemo");
        requestDiaryUpdateDTO.setEcdysis(true);
        requestDiaryUpdateDTO.setCleaning(false);
        requestDiaryUpdateDTO.setShower(true);
        requestDiaryUpdateDTO.setBowelMovement(false);

        // 테스트 대상 메소드 호출
        DiaryDAO updatedDiaryDAO = updateDiaryDAOBean.exec(diaryDAO, requestDiaryUpdateDTO);

        // 검증
        assertEquals("[{\"imageUrl\":\"testImageUrl.png\"}]", updatedDiaryDAO.getImageUrl());
        assertEquals("fakeFood", updatedDiaryDAO.getFood());
        assertEquals(3, updatedDiaryDAO.getFoodCounter());
        assertEquals("fakeSize", updatedDiaryDAO.getSize());
        assertEquals(5.0, updatedDiaryDAO.getWeight());
        assertEquals("fakeMemo", updatedDiaryDAO.getMemo());
        assertEquals(true, updatedDiaryDAO.isEcdysis());
        assertEquals(false, updatedDiaryDAO.isCleaning());
        assertEquals(true, updatedDiaryDAO.isShower());
        assertEquals(false, updatedDiaryDAO.isBowelMovement());
    }
}
