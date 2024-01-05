package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveDiaryBean {

    CreateUniqueIdBean createUniqueIdBean;
    UpdatePetDAOBean updatePetDAOBean;
    UpdateUserDiaryCountDAOBean updateUserDiaryCountDAOBean;
    SaveDiaryDAOBean saveDiaryDAOBean;
    SavePetDAOBean savePetDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveDiaryBean(CreateUniqueIdBean createUniqueIdBean, UpdatePetDAOBean updatePetDAOBean, UpdateUserDiaryCountDAOBean updateUserDiaryCountDAOBean, SaveDiaryDAOBean saveDiaryDAOBean, SavePetDAOBean savePetDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.updatePetDAOBean = updatePetDAOBean;
        this.updateUserDiaryCountDAOBean = updateUserDiaryCountDAOBean;
        this.saveDiaryDAOBean = saveDiaryDAOBean;
        this.savePetDAOBean = savePetDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 다이어리 저장
    public Long exec(RequestDiarySaveDTO requestDiarySaveDTO){

        // diaryId 생성
        Long diaryId = createUniqueIdBean.exec();

        // 다이어리 저장
        DiaryDAO diaryDAO = saveDiaryDAOBean.exec(diaryId, requestDiarySaveDTO);

        // 다이어리 저장시 다이어리 갯수, 펫 몸무게, 경험치 업데이트
        PetDAO petDAO = updatePetDAOBean.exec(diaryDAO);
        if (petDAO == null) return 0L;

        // 유저 다이어리 갯수 추가
        UserDAO userDAO = updateUserDiaryCountDAOBean.exec(petDAO);
        if (userDAO == null) return 0L;

        // 다이어리 저장
        saveDiaryDAOBean.exec(diaryDAO);

        // 펫 저장
        savePetDAOBean.exec(petDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // 다이어리 diaryId 반환
        return diaryId;
    }
}
