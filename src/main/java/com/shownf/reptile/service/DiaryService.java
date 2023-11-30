package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestDiaryDTO;
import com.shownf.reptile.Model.DTO.RequestDiaryDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.Model.DTO.ResponseDiarysDTO;
import com.shownf.reptile.bean.DeleteDiaryBean;
import com.shownf.reptile.bean.GetDiaryBean;
import com.shownf.reptile.bean.GetDiarysBean;
import com.shownf.reptile.bean.SaveDiaryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    GetDiaryBean getDiaryBean;
    GetDiarysBean getDiarysBean;
    SaveDiaryBean saveDiaryBean;
    DeleteDiaryBean deleteDiaryBean;

    @Autowired
    public DiaryService(GetDiaryBean getDiaryBean, GetDiarysBean getDiarysBean, SaveDiaryBean saveDiaryBean, DeleteDiaryBean deleteDiaryBean) {
        this.getDiaryBean = getDiaryBean;
        this.getDiarysBean = getDiarysBean;
        this.saveDiaryBean = saveDiaryBean;
        this.deleteDiaryBean = deleteDiaryBean;
    }

    // 다이어리 조회
    public RequestDiaryDTO getDiary(Long diaryId){
        return getDiaryBean.exec(diaryId);
    }

    // 다이어리 월별로 조회
    public List<ResponseDiarysDTO> getDiarys(Long petId, String year, String month){
        return getDiarysBean.exec(petId, year, month);
    }

    // 다이어리 저장
    public Long saveDiary(RequestDiarySaveDTO requestDiarySaveDTO){
        return saveDiaryBean.exec(requestDiarySaveDTO);
    }

    // 다이어리 삭제
    public Long deleteDiary(RequestDiaryDeleteDTO requestDiaryDeleteDTO){
        return deleteDiaryBean.exec(requestDiaryDeleteDTO);
    }
}
