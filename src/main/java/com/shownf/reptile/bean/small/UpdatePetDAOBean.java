package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.DTO.RequestDiaryDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPetUpdateDTO;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class UpdatePetDAOBean {

    GetPetDAOBean getPetDAOBean;
    CheckLevelPetDAOBean checkLevelPetDAOBean;

    @Autowired
    public UpdatePetDAOBean(GetPetDAOBean getPetDAOBean, CheckLevelPetDAOBean checkLevelPetDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.checkLevelPetDAOBean = checkLevelPetDAOBean;
    }

    // pet update
    public PetDAO exec(PetDAO petDAO, RequestPetUpdateDTO requestPetUpdateDTO){

        // 이미지 Url
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            petDAO.setImageUrl(objectMapper.writeValueAsString(requestPetUpdateDTO.getImageUrl()));
        }catch (IOException e){
            e.printStackTrace();
        }

        // 이름
        petDAO.setName(requestPetUpdateDTO.getName());

        // 첫번째 종
        petDAO.setFirstSpecies(requestPetUpdateDTO.getFirstSpecies());

        // 두번째 종
        petDAO.setSecondSpecies(requestPetUpdateDTO.getSecondSpecies());

        // 생일
        String date = requestPetUpdateDTO.getBirthday();
        String birthday;
        String[] words = date.split(" ");
        if (words[1].length() < 2) words[1] = "0" + words[1];
        if (words[2].length() < 2) words[2] = "0" + words[2];
        birthday = words[0] + words[1] + words[2];
        petDAO.setBirthday(birthday);

        // 몸무게
        petDAO.setWeight(requestPetUpdateDTO.getWeight());

        // 성별
        petDAO.setGender(Gender.valueOf(requestPetUpdateDTO.getGender()));

        // 수정 시간
        petDAO.setUpdateTime(LocalDateTime.now());

        return petDAO;
    }

    // 다이어리 추가시 마이펫 다이어리 갯수, 몸무게, 경험치 수정
    public PetDAO exec(DiaryDAO diaryDAO){

        // 펫 아이디
        Long petId = diaryDAO.getPetId();

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return null;

        // 펫 다이어리 갯수 추가
        petDAO.setDiaryCount(petDAO.getDiaryCount() + 1);

        // 펫 이미지 세팅
        petDAO.setImageUrl(diaryDAO.getImageUrl());

        // 펫 몸무게 세팅
        petDAO.setWeight(diaryDAO.getWeight());

        // 펫 경험치 추가
        petDAO.setLevelExperience(petDAO.getLevelExperience() + 10);

        // 펫 경험치에 따른 레벨 확인
        return checkLevelPetDAOBean.exec(petDAO);
    }

    // 다이어리 수정시 몸무게 수정
    public PetDAO exec(Long weight, DiaryDAO diaryDAO){

        // 펫 아이디
        Long petId = diaryDAO.getPetId();

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return null;

        // 펫 이미지 세팅
        petDAO.setImageUrl(diaryDAO.getImageUrl());
        
        // 펫 몸무게 세팅
        petDAO.setWeight(diaryDAO.getWeight());

        return petDAO;
    }

    // 다이어리 삭제시 마이펫 다이어리 갯수 수정
    public PetDAO exec(RequestDiaryDeleteDTO requestDiaryDeleteDTO){

        // 펫 아이디
        Long petId = requestDiaryDeleteDTO.getPetId();

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return null;

        // 펫 다이어리 갯수 감소
        petDAO.setDiaryCount(petDAO.getDiaryCount() - 1);

        // 펫 경험치 감소
        petDAO.setLevelExperience(petDAO.getLevelExperience() - 10);

        // 펫 경험치에 따른 레벨 확인
        return checkLevelPetDAOBean.exec(petDAO);
    }
}
