package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetWeightDAOBean {

    GetPetDAOBean getPetDAOBean;
    SavePetDAOBean savePetDAOBean;

    @Autowired
    public UpdatePetWeightDAOBean(GetPetDAOBean getPetDAOBean, SavePetDAOBean savePetDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.savePetDAOBean = savePetDAOBean;
    }

    // 다이어리 저장시 몸무게 업데이트
    public void exec(RequestDiarySaveDTO requestDiarySaveDTO){

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(requestDiarySaveDTO.getPetId());

        // 펫 몸무게 세팅
        petDAO.setWeight(requestDiarySaveDTO.getWeight());

        // 펫 저장
        savePetDAOBean.exec(petDAO);
    }
}
