package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSalePetUpdateDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdatePetUserNameBean {

    GetPetDAOBean getPetDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    SavePetDAOBean savePetDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public UpdatePetUserNameBean(GetPetDAOBean getPetDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, SavePetDAOBean savePetDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.savePetDAOBean = savePetDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 분양시 유저 아이디 변경
    public Long exec(RequestSalePetUpdateDTO requestSalePetUpdateDTO, HttpServletRequest request){

        // 펫 객체 찾기
        PetDAO petDAO = getPetDAOBean.exec(requestSalePetUpdateDTO.getPetId());
        if (petDAO == null) return 0L;

        // 펫 주인 유저 찾기
        UserDAO userDAO1 = getUserDAOBean.exec(petDAO.getUserId());
        if (userDAO1 == null) return 0L;

        // 펫 유저 아이디와 유저 토큰 확인
        if (!checkUserAccessTokenDAOBean.exec(userDAO1, request))
            return 0L;

        // 분양받는 유저 찾기
        UserDAO userDAO2 = getUserDAOBean.exec(requestSalePetUpdateDTO.getUserId());

        // 펫 유저 아이디 변경
        petDAO.setUserId(requestSalePetUpdateDTO.getUserId());

        // 유저 마이펫 수 변경
        userDAO1.setPetCount(userDAO1.getPetCount()-1);
        userDAO2.setPetCount(userDAO2.getPetCount()+1);

        // 펫 저장
        savePetDAOBean.exec(petDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        return requestSalePetUpdateDTO.getPetId();
    }
}
