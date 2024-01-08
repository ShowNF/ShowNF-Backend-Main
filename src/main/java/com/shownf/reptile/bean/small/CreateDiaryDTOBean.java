package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseDiaryDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import org.springframework.stereotype.Component;

@Component
public class CreateDiaryDTOBean {

    // 다이어리 조회시 DTO 생성
    public ResponseDiaryDTO exec(DiaryDAO diaryDAO){
        ResponseDiaryDTO responseDiaryDTO = new ResponseDiaryDTO();

        // DTO 객체에 다이어리 정보 넘기기
        responseDiaryDTO.setDiaryId(diaryDAO.getDiaryId());
        responseDiaryDTO.setPetId(diaryDAO.getPetId());
        responseDiaryDTO.setImageUrl(diaryDAO.getImageUrl());
        responseDiaryDTO.setFood(diaryDAO.getFood());
        responseDiaryDTO.setFoodCounter(diaryDAO.getFoodCounter());
        responseDiaryDTO.setSize(diaryDAO.getSize());
        responseDiaryDTO.setWeight(diaryDAO.getWeight());
        responseDiaryDTO.setMemo(diaryDAO.getMemo());
        responseDiaryDTO.setUploadTime(diaryDAO.getUploadTime());
        responseDiaryDTO.setUpdateTime(diaryDAO.getUpdateTime());
        responseDiaryDTO.setDate(diaryDAO.getDate());
        responseDiaryDTO.setMonth(diaryDAO.getMonth());
        responseDiaryDTO.setEcdysis(diaryDAO.isEcdysis());
        responseDiaryDTO.setCleaning(diaryDAO.isCleaning());
        responseDiaryDTO.setShower(diaryDAO.isShower());
        responseDiaryDTO.setBowelMovement(diaryDAO.isBowelMovement());

        return responseDiaryDTO;
    }
}
