package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestPetDeleteDTO;
import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.DTO.RequestPetSaveDTO;
import com.shownf.reptile.bean.DeletePetBean;
import com.shownf.reptile.bean.GetPetBean;
import com.shownf.reptile.bean.GetPetsBean;
import com.shownf.reptile.bean.SavePetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    GetPetBean getPetBean;
    GetPetsBean getPetsBean;
    SavePetBean savePetBean;
    DeletePetBean deletePetBean;

    @Autowired
    public PetService(GetPetBean getPetBean, GetPetsBean getPetsBean, SavePetBean savePetBean, DeletePetBean deletePetBean) {
        this.getPetBean = getPetBean;
        this.getPetsBean = getPetsBean;
        this.savePetBean = savePetBean;
        this.deletePetBean = deletePetBean;
    }

    // 마이펫 조회
    public ResponsePetDTO getPet(Long petId){
        return getPetBean.exec(petId);
    }

    // 마이펫 전체 조회
    public Page<ResponsePetDTO> getPets(Long userId, Pageable pageable){
        return getPetsBean.exec(userId, pageable);
    }

    // 마이펫 저장
    public Long savePet(RequestPetSaveDTO requestPetSaveDTO){
        return savePetBean.exec(requestPetSaveDTO);
    }

    // 마이펫 삭제
    public Long deletePet(RequestPetDeleteDTO requestPetDeleteDTO){
        return deletePetBean.exec(requestPetDeleteDTO);
    }
}