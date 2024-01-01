package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.stereotype.Component;

@Component
public class CreatePetDTOBean {

    // 마이펫 조회 시 DTO 생성
    public ResponsePetDTO exec(PetDAO petDAO){
        ResponsePetDTO responsePetDTO = new ResponsePetDTO();

        // DTO 객체에 마이펫 정보 넘기기
        responsePetDTO.setPetId(petDAO.getPetId());
        responsePetDTO.setUserId(petDAO.getUserId());
        responsePetDTO.setImageUrl(petDAO.getImageUrl());
        responsePetDTO.setName(petDAO.getName());
        responsePetDTO.setFirstSpecies(petDAO.getFirstSpecies());
        responsePetDTO.setSecondSpecies(petDAO.getSecondSpecies());
        responsePetDTO.setBirthday(petDAO.getBirthday());
        responsePetDTO.setWeight(petDAO.getWeight());
        responsePetDTO.setGender(petDAO.getGender().name());
        responsePetDTO.setUploadTime(petDAO.getUploadTime());
        responsePetDTO.setDiaryCount(petDAO.getDiaryCount());
        responsePetDTO.setLevel(petDAO.getLevel());
        responsePetDTO.setLevelExperience(petDAO.getLevelExperience());

        // DTO 반환
        return responsePetDTO;
    }
}
