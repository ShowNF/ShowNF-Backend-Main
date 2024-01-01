package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestDiaryDeleteDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDiaryBean {

    GetDiaryDAOBean getDiaryDAOBean;
    UpdatePetDAOBean updatePetDAOBean;
    UpdateUserDiaryCountDAOBean updateUserDiaryCountDAOBean;
    SaveDiaryDAOBean saveDiaryDAOBean;
    SavePetDAOBean savePetDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteDiaryBean(GetDiaryDAOBean getDiaryDAOBean, UpdatePetDAOBean updatePetDAOBean, UpdateUserDiaryCountDAOBean updateUserDiaryCountDAOBean, SaveDiaryDAOBean saveDiaryDAOBean, SavePetDAOBean savePetDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getDiaryDAOBean = getDiaryDAOBean;
        this.updatePetDAOBean = updatePetDAOBean;
        this.updateUserDiaryCountDAOBean = updateUserDiaryCountDAOBean;
        this.saveDiaryDAOBean = saveDiaryDAOBean;
        this.savePetDAOBean = savePetDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 다이어리 삭제
    public Long exec(RequestDiaryDeleteDTO requestDiaryDeleteDTO){

        // 다이어리 아이디
        Long diaryId = requestDiaryDeleteDTO.getDiaryId();

        // 삭제할 다이어리 찾기
        DiaryDAO diaryDAO = getDiaryDAOBean.exec(diaryId);
        if (diaryDAO == null) return 0L;

        // 다이어리 deleteCheck 값 true 설정
        diaryDAO.setDeleteCheck(true);

        // 펫 다이어리 수, 겸험치 감소
        PetDAO petDAO = updatePetDAOBean.exec(requestDiaryDeleteDTO);
        if (petDAO == null) return 0L;

        // 유저 다이어리 수 감소
        UserDAO userDAO = updateUserDiaryCountDAOBean.exec(null, petDAO);

        // 다이어리 저장
        saveDiaryDAOBean.exec(diaryDAO);

        // 펫 저장
        savePetDAOBean.exec(petDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        return diaryId;
    }
}
