package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPetDisclosureDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdatePetDisclosureBean {

    GetPetDAOBean getPetDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdatePetDAOBean updatePetDAOBean;
    SavePetDAOBean savePetDAOBean;

    @Autowired
    public UpdatePetDisclosureBean(GetPetDAOBean getPetDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdatePetDAOBean updatePetDAOBean, SavePetDAOBean savePetDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updatePetDAOBean = updatePetDAOBean;
        this.savePetDAOBean = savePetDAOBean;
    }

    // Update the pet disclosure
    public Long exec(RequestPetDisclosureDTO requestPetDisclosureDTO, HttpServletRequest request){

        // 펫 가져오기
        PetDAO petDAO = getPetDAOBean.exec(requestPetDisclosureDTO.getPetId());
        if (petDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestPetDisclosureDTO.getUserId());
        if (userDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return null;

        // 펫 수정
        PetDAO updatePetDAO = updatePetDAOBean.exec(petDAO, requestPetDisclosureDTO);

        // 마이펫 저장
        savePetDAOBean.exec(updatePetDAO);

        // 마이펫 petId 반환
        return requestPetDisclosureDTO.getPetId();
    }
}
