package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPetDeleteDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeletePetBean {

    GetPetDAOBean getPetDAOBean;
    GetDiarysDAOBean getDiarysDAOBean;
    UpdateUserPetCountDAOBean updateUserPetCountDAOBean;
    SavePetDAOBean savePetDAOBean;
    SaveDiaryDAOBean saveDiaryDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeletePetBean(GetPetDAOBean getPetDAOBean, GetDiarysDAOBean getDiarysDAOBean, UpdateUserPetCountDAOBean updateUserPetCountDAOBean, SavePetDAOBean savePetDAOBean, SaveDiaryDAOBean saveDiaryDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPetDAOBean = getPetDAOBean;
        this.getDiarysDAOBean = getDiarysDAOBean;
        this.updateUserPetCountDAOBean = updateUserPetCountDAOBean;
        this.savePetDAOBean = savePetDAOBean;
        this.saveDiaryDAOBean = saveDiaryDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 마이펫 삭제
    public Long exec(RequestPetDeleteDTO requestPetDeleteDTO){

        // 펫 아이디
        Long petId = requestPetDeleteDTO.getPetId();

        // 삭제할 펫 찾기
        PetDAO petDAO = getPetDAOBean.exec(petId);
        if (petDAO == null) return 0L;

        // 펫 deleteCheck 값 true 설정
        petDAO.setDeleteCheck(true);

        // 펫 삭제에 따른 다이어리 deleteCheck 설정
        List<DiaryDAO> diaryDAOS = getDiarysDAOBean.exec(petDAO);
        Integer diaryCount = diaryDAOS.size();

        // 유저 펫, 다이어리 수 감소
        UserDAO userDAO = updateUserPetCountDAOBean.exec(diaryCount, petDAO);
        if (userDAO == null) return 0L;

        // 펫 저장
        savePetDAOBean.exec(petDAO);

        // 다이어리 저장
        saveDiaryDAOBean.exec(diaryDAOS);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        return petId;
    }
}
