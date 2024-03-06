package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CheckUserAccessTokenDAOBean;
import com.shownf.reptile.bean.small.CreatePetDTOBean;
import com.shownf.reptile.bean.small.GetPetsDAOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetPetsBean {

    GetPetsDAOBean getPetsDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    CreatePetDTOBean createPetDTOBean;

    @Autowired
    public GetPetsBean(GetPetsDAOBean getPetsDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, CreatePetDTOBean createPetDTOBean) {
        this.getPetsDAOBean = getPetsDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.createPetDTOBean = createPetDTOBean;
    }

    // 마이펫 Page 형태로 전체 조회
    public Page<ResponsePetDTO> exec(Long userId, Pageable pageable, HttpServletRequest request){

        // 유저 아이디로 마이펫 전체 찾기
        Page<PetDAO> petDAOs = getPetsDAOBean.exec(userId, pageable);

        // 마이페이지인지 남의 마이펫인지 구분하기 위해 token 확인
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        boolean check = checkUserAccessTokenDAOBean.exec(userDAO, request);

        // DAO 객체 DTO 반환
        return createPetDTOBean.exec(check, pageable, petDAOs);
    }

    // 마이펫 Page 형태로 레벨별 조회
    public Page<ResponsePetDTO> exec(Long userId, Pageable pageable, Integer level, HttpServletRequest request){

        // 유저 아이디로 마이펫 전체 찾기
        Page<PetDAO> petDAOs = getPetsDAOBean.exec(userId, pageable);

        // 마이페이지인지 남의 마이펫인지 구분하기 위해 token 확인
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        boolean check = checkUserAccessTokenDAOBean.exec(userDAO, request);

        // DAO 객체 DTO 반환
        return createPetDTOBean.exec(check, pageable, petDAOs);
    }
}
