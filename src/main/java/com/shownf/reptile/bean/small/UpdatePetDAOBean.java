package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestDiaryDeleteDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetDAOBean {

    GetPetDAOBean getPetDAOBean;
    CheckLevelPetDAOBean checkLevelPetDAOBean;

    @Autowired
    public UpdatePetDAOBean(GetPetDAOBean getPetDAOBean, CheckLevelPetDAOBean checkLevelPetDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.checkLevelPetDAOBean = checkLevelPetDAOBean;
    }

    // 다이어리 추가시 마이펫 다이어리 갯수, 몸무게, 경험치 수정
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

        // 펫 경험치 추가
        petDAO.setLevelExperience(petDAO.getLevelExperience() + 10);

        // 펫 경험치에 따른 레벨 확인
        return checkLevelPetDAOBean.exec(petDAO);
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

    // 다이어리 삭제시 마이펫 다이어리 갯수 수정
    public PetDAO exec(RequestDiaryDeleteDTO requestDiaryDeleteDTO){

        // 펫 아이디
        Long petId = requestDiaryDeleteDTO.getPetId();

        // 펫아이디로 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return null;

        // 펫 다이어리 갯수 감소
        petDAO.setDiaryCount(petDAO.getDiaryCount() - 1);

        // 펫 경험치 감소
        petDAO.setLevelExperience(petDAO.getLevelExperience() - 10);

        // 펫 경험치에 따른 레벨 확인
        return checkLevelPetDAOBean.exec(petDAO);
    }
}
