package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class SaveDiaryDAOBean {

    DiaryRepositoryJPA diaryRepositoryJPA;

    @Autowired
    public SaveDiaryDAOBean(DiaryRepositoryJPA diaryRepositoryJPA) {
        this.diaryRepositoryJPA = diaryRepositoryJPA;
    }

    // 다이어리 저장
    public void exec(DiaryDAO diaryDAO){
        diaryRepositoryJPA.save(diaryDAO);
    }

    // 다이어리 저장시 DAO 생성
    public DiaryDAO exec(Long diaryId, RequestDiarySaveDTO requestDiarySaveDTO){

        // 마이펫 아이디
        Long petId = requestDiarySaveDTO.getPetId();

        // 마이펫 이미지
        String imageUrl = requestDiarySaveDTO.getImageUrl();

        // 먹이
        String food = requestDiarySaveDTO.getFood();

        // 먹이 수
        Integer foodCounter = requestDiarySaveDTO.getFoodCounter();

        // 먹이 크기
        String size = requestDiarySaveDTO.getSize();

        // 몸무게
        Double weight = requestDiarySaveDTO.getWeight();

        // 메모
        String memo = requestDiarySaveDTO.getMemo();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 날짜
        String date = requestDiarySaveDTO.getDate();

        // 년, 월
        String month;
        String[] words = date.split(" ");
        if (words[1].length() < 2) month = words[0] + "0" + words[1];
        else month = words[0] + words[1];

        // 탈피 여부
        boolean ecdysis = requestDiarySaveDTO.isEcdysis();

        // 청소여부
        boolean cleaning = requestDiarySaveDTO.isCleaning();

        // 샤워 여부
        boolean shower = requestDiarySaveDTO.isShower();
        // 배변 여부
        boolean bowelMovement = requestDiarySaveDTO.isBowelMovement();

        return new DiaryDAO(diaryId, petId, imageUrl, food, foodCounter, size, weight, memo, uploadTime, date, month, ecdysis, cleaning, shower, bowelMovement);
    }

    // 다이어리 수정시 저장
    public DiaryDAO exec(RequestDiarySaveDTO requestDiarySaveDTO){

        DiaryDAO diaryDAO = diaryRepositoryJPA.findById(requestDiarySaveDTO.getDiaryId()).get();

        // 마이펫 이미지
        diaryDAO.setImageUrl(requestDiarySaveDTO.getImageUrl());

        // 먹이
        diaryDAO.setFood(requestDiarySaveDTO.getFood());

        // 먹이 수
        diaryDAO.setFoodCounter(requestDiarySaveDTO.getFoodCounter());

        // 먹이 크기
        diaryDAO.setSize(requestDiarySaveDTO.getSize());

        // 몸무게
        diaryDAO.setWeight(requestDiarySaveDTO.getWeight());

        // 메모
        diaryDAO.setMemo(requestDiarySaveDTO.getMemo());

        // 업로드 시간
        diaryDAO.setUploadTime(LocalDateTime.now());

        // 날짜
        String date = requestDiarySaveDTO.getDate();
        diaryDAO.setDate(date);

        // 년, 월
        String month;
        String[] words = date.split(" ");
        if (words[1].length() < 2) month = words[0] + "0" + words[1];
        else month = words[0] + words[1];
        diaryDAO.setMonth(month);

        // 탈피 여부
        diaryDAO.setEcdysis(requestDiarySaveDTO.isEcdysis());

        // 청소 여부
        diaryDAO.setCleaning(requestDiarySaveDTO.isCleaning());

        // 샤워 여부
        diaryDAO.setShower(requestDiarySaveDTO.isShower());

        // 배변 여부
        diaryDAO.setBowelMovement(requestDiarySaveDTO.isBowelMovement());

        return diaryDAO;
    }
}
