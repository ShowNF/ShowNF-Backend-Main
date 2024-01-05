package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestDiaryUpdateDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateDiaryBean {

    GetDiaryDAOBean getDiaryDAOBean;
    UpdateDiaryDAOBean updateDiaryDAOBean;
    UpdatePetDAOBean updatePetDAOBean;
    SaveDiaryDAOBean saveDiaryDAOBean;
    SavePetDAOBean savePetDAOBean;

    @Autowired
    public UpdateDiaryBean(GetDiaryDAOBean getDiaryDAOBean, UpdateDiaryDAOBean updateDiaryDAOBean, UpdatePetDAOBean updatePetDAOBean, SaveDiaryDAOBean saveDiaryDAOBean, SavePetDAOBean savePetDAOBean) {
        this.getDiaryDAOBean = getDiaryDAOBean;
        this.updateDiaryDAOBean = updateDiaryDAOBean;
        this.updatePetDAOBean = updatePetDAOBean;
        this.saveDiaryDAOBean = saveDiaryDAOBean;
        this.savePetDAOBean = savePetDAOBean;
    }

    // 다이어리 저장
    public Long exec(RequestDiaryUpdateDTO requestDiaryUpdateDTO){

        // diaryId
        Long diaryId = requestDiaryUpdateDTO.getDiaryId();

        // 다이어리 가져오기
        DiaryDAO diaryDAO = getDiaryDAOBean.exec(diaryId);

        // 다이어리 수정
        DiaryDAO updateDiaryDAO = updateDiaryDAOBean.exec(diaryDAO, requestDiaryUpdateDTO);

        // 다이어리 저장시 펫 몸무게 업데이트
        PetDAO petDAO = updatePetDAOBean.exec(null, diaryDAO);

        // 다이어리 저장
        saveDiaryDAOBean.exec(updateDiaryDAO);

        // 펫 저장
        savePetDAOBean.exec(petDAO);

        return requestDiaryUpdateDTO.getDiaryId();
    }
}
