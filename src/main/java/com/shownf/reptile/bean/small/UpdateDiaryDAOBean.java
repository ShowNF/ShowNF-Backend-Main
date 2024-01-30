package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.DTO.RequestDiaryUpdateDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class UpdateDiaryDAOBean {

    // 다이어리 수정시 저장
    public DiaryDAO exec(DiaryDAO diaryDAO, RequestDiaryUpdateDTO requestDiaryUpdateDTO){

        // 마이펫 이미지
        ObjectMapper objectMapper = new ObjectMapper();
        String imageUrl = "";
        try {
            if (!requestDiaryUpdateDTO.getImageUrl().toString().equals(""))
                imageUrl = objectMapper.writeValueAsString(requestDiaryUpdateDTO.getImageUrl());
        }catch (IOException e){
            e.printStackTrace();
        }
        // 마이펫 이미지
        diaryDAO.setImageUrl(imageUrl);

        // 먹이
        diaryDAO.setFood(requestDiaryUpdateDTO.getFood());

        // 먹이 수
        diaryDAO.setFoodCounter(requestDiaryUpdateDTO.getFoodCounter());

        // 먹이 크기
        diaryDAO.setSize(requestDiaryUpdateDTO.getSize());

        // 몸무게
        diaryDAO.setWeight(requestDiaryUpdateDTO.getWeight());

        // 메모
        diaryDAO.setMemo(requestDiaryUpdateDTO.getMemo());

        // 수정 시간
        diaryDAO.setUpdateTime(LocalDateTime.now());

        // 탈피 여부
        diaryDAO.setEcdysis(requestDiaryUpdateDTO.isEcdysis());

        // 청소 여부
        diaryDAO.setCleaning(requestDiaryUpdateDTO.isCleaning());

        // 샤워 여부
        diaryDAO.setShower(requestDiaryUpdateDTO.isShower());

        // 배변 여부
        diaryDAO.setBowelMovement(requestDiaryUpdateDTO.isBowelMovement());

        return diaryDAO;
    }
}
