package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PetService {

    GetPetBean getPetBean;
    GetPetsBean getPetsBean;
    SavePetBean savePetBean;
    UpdatePetBean updatePetBean;
    UpdatePetDisclosureBean updatePetDisclosureBean;
    DeletePetBean deletePetBean;

    @Autowired
    public PetService(GetPetBean getPetBean, GetPetsBean getPetsBean, SavePetBean savePetBean, UpdatePetBean updatePetBean, UpdatePetDisclosureBean updatePetDisclosureBean, DeletePetBean deletePetBean) {
        this.getPetBean = getPetBean;
        this.getPetsBean = getPetsBean;
        this.savePetBean = savePetBean;
        this.updatePetBean = updatePetBean;
        this.updatePetDisclosureBean = updatePetDisclosureBean;
        this.deletePetBean = deletePetBean;
    }

    // 마이펫 조회
    public ResponsePetDTO getPet(Long petId){
        return getPetBean.exec(petId);
    }

    // 마이펫 전체 조회
    public Page<ResponsePetDTO> getPets(Long userId, Pageable pageable, HttpServletRequest request){
        return getPetsBean.exec(userId, pageable, request);
    }

    // 마이펫 레벨별 조회
    public Page<ResponsePetDTO> getLevelPets(Long userId, Pageable pageable, HttpServletRequest request){
        return getPetsBean.exec(userId, pageable, 0, request);
    }

    // 마이펫 저장
    public Long savePet(RequestPetSaveDTO requestPetSaveDTO){
        return savePetBean.exec(requestPetSaveDTO);
    }

    // 마이펫 수정
    public Long updatePet(RequestPetUpdateDTO requestPetUpdateDTO){
        return updatePetBean.exec(requestPetUpdateDTO);
    }

    // 마이펫 수정
    public Long updatePetDisclosure(RequestPetDisclosureDTO requestPetDisclosureDTO, HttpServletRequest request){
        return updatePetDisclosureBean.exec(requestPetDisclosureDTO, request);
    }

    // 마이펫 삭제
    public Long deletePet(RequestPetDeleteDTO requestPetDeleteDTO){
        return deletePetBean.exec(requestPetDeleteDTO);
    }
}