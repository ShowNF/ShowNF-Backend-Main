package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPetUpdateDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.bean.small.GetPetDAOBean;
import com.shownf.reptile.bean.small.SavePetDAOBean;
import com.shownf.reptile.bean.small.UpdatePetDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetBean {

    GetPetDAOBean getPetDAOBean;
    UpdatePetDAOBean updatePetDAOBean;
    SavePetDAOBean savePetDAOBean;

    @Autowired
    public UpdatePetBean(GetPetDAOBean getPetDAOBean, UpdatePetDAOBean updatePetDAOBean, SavePetDAOBean savePetDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.updatePetDAOBean = updatePetDAOBean;
        this.savePetDAOBean = savePetDAOBean;
    }

    // pet update
    public Long exec(RequestPetUpdateDTO requestPetUpdateDTO){

        // 펫 가져오기
        PetDAO petDAO = getPetDAOBean.exec(requestPetUpdateDTO.getPetId());
        if (petDAO == null) return 0L;

        // 펫 수정
        PetDAO updatePetDAO = updatePetDAOBean.exec(petDAO, requestPetUpdateDTO);

        // 마이펫 저장
        savePetDAOBean.exec(updatePetDAO);

        // 마이펫 petId 반환
        return requestPetUpdateDTO.getPetId();
    }
}
