package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.SaveDiaryDAOBean;
import com.shownf.reptile.bean.small.UpdatePetWeightDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveDiaryBean {

    CreateUniqueIdBean createUniqueIdBean;
    SaveDiaryDAOBean saveDiaryDAOBean;
    UpdatePetWeightDAOBean updatePetWeightDAOBean;

    @Autowired
    public SaveDiaryBean(CreateUniqueIdBean createUniqueIdBean, SaveDiaryDAOBean saveDiaryDAOBean, UpdatePetWeightDAOBean updatePetWeightDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.saveDiaryDAOBean = saveDiaryDAOBean;
        this.updatePetWeightDAOBean = updatePetWeightDAOBean;
    }

    // 다이어리 저장
    public Long exec(RequestDiarySaveDTO requestDiarySaveDTO){

        // 다이어리 수정시 저장
        if (requestDiarySaveDTO.getDiaryId() != null){
            saveDiaryDAOBean.exec(requestDiarySaveDTO);

            // 다이어리 저장시 펫 몸무게 업데이트
            updatePetWeightDAOBean.exec(requestDiarySaveDTO);

            return requestDiarySaveDTO.getDiaryId();
        }

        // diaryId 생성
        Long diaryId = createUniqueIdBean.exec();

        // 다이어리 저장
        saveDiaryDAOBean.exec(diaryId, requestDiarySaveDTO);

        // 다이어리 저장시 펫 몸무게 업데이트
        updatePetWeightDAOBean.exec(requestDiarySaveDTO);

        // 다이어리 diaryId 반환
        return diaryId;
    }
}
