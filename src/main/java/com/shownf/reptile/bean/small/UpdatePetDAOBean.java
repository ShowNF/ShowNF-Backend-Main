package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetDAOBean {

    GetPetDAOBean getPetDAOBean;

    @Autowired
    public UpdatePetDAOBean(GetPetDAOBean getPetDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
    }

    // 다이어리 추가시 마이펫 다이어리 갯수, 몸무게 수정
    public PetDAO exec(DiaryDAO diaryDAO){

        // 펫 아이디
        Long petId = diaryDAO.getPetId();

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return null;

        // 펫 다이어리 갯수 추가
        petDAO.setDiaryCount(petDAO.getDiaryCount() + 1);

        // 펫 몸무게 세팅
        petDAO.setWeight(diaryDAO.getWeight());

        return petDAO;
    }

    // 다이어리 수정시 몸무게 수정
    public PetDAO exec(Long weight, DiaryDAO diaryDAO){

        // 펫 아이디
        Long petId = diaryDAO.getPetId();

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return null;

        // 펫 몸무게 세팅
        petDAO.setWeight(diaryDAO.getWeight());

        return petDAO;
    }
}
