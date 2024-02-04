package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreatePetsDTOBean {

    // 유저 아이디로 마이펫 조회시 DTO 생성
    public Page<ResponsePetDTO> exec(boolean check, Pageable pageable, Page<PetDAO> petDAOs){

        List<ResponsePetDTO> responsePetDTOS = new ArrayList<>();

        // DTO 객체에 게시물 정보 넘기기
        for (PetDAO petDAO: petDAOs) {
            if (petDAO.isDeleteCheck()) continue;
            // check 가 true이면 자기꺼니까 걍 다 보이고 false 이면 내꺼가 아니니까
            if (!check && petDAO.getDisclosure().toString().equals("나만보기")) continue;
            ResponsePetDTO responsePetDTO = new ResponsePetDTO();

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
            responsePetDTO.setUpdateTime(petDAO.getUpdateTime());
            responsePetDTO.setDiaryCount(petDAO.getDiaryCount());
            responsePetDTO.setLevel(petDAO.getLevel());
            responsePetDTO.setLevelExperience(petDAO.getLevelExperience());
            responsePetDTO.setDisclosure(petDAO.getDisclosure().name());

            responsePetDTOS.add(responsePetDTO);
        }

        // List 구조를 Page 구조로 변경 후 반환
        return new PageImpl<>(responsePetDTOS, pageable, petDAOs.getTotalElements());
    }
}
