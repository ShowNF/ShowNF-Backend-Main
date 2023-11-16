package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPetSaveDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.SavePetDAOBean;
import com.shownf.reptile.bean.small.SaveUserDAOBean;
import com.shownf.reptile.bean.small.UpdateUserPetCountDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePetBean {

    CreateUniqueIdBean createUniqueIdBean;
    UpdateUserPetCountDAOBean updateUserPetCountDAOBean;
    SavePetDAOBean savePetDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SavePetBean(CreateUniqueIdBean createUniqueIdBean, UpdateUserPetCountDAOBean updateUserPetCountDAOBean, SavePetDAOBean savePetDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.updateUserPetCountDAOBean = updateUserPetCountDAOBean;
        this.savePetDAOBean = savePetDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public Long exec(RequestPetSaveDTO requestPetSaveDTO){

        // petId 생성
        Long petId = createUniqueIdBean.exec();

        // 마이펫 저장
        PetDAO petDAO = savePetDAOBean.exec(petId, requestPetSaveDTO);

        // 마이펫 저장시 유저 petCount 증가
        UserDAO userDAO = updateUserPetCountDAOBean.exec(petDAO);
        if (userDAO == null) return 0L;

        // 마이펫 저장
        savePetDAOBean.exec(petDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // 마이펫 petId 반환
        return petId;
    }
}
