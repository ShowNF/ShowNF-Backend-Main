package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseDiarysDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.bean.small.CreateDiarysDTOBean;
import com.shownf.reptile.bean.small.GetDiarysDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDiarysBean {

    GetDiarysDAOBean getDiarysDAOBean;
    CreateDiarysDTOBean createDiarysDTOBean;

    @Autowired
    public GetDiarysBean(GetDiarysDAOBean getDiarysDAOBean, CreateDiarysDTOBean createDiarysDTOBean) {
        this.getDiarysDAOBean = getDiarysDAOBean;
        this.createDiarysDTOBean = createDiarysDTOBean;
    }

    // 다이어리 월별로 조회
    public List<ResponseDiarysDTO> exec(Long petId, String year, String month){

        // year, month 합치기
        String yearMonth;
        if (month.length() < 2) yearMonth = year + "0" + month;
        else yearMonth = year + month;

        // 펫아이디와 월로 다이어리 객체 찾기
        List<DiaryDAO> diaryDAOs = getDiarysDAOBean.exec(petId, yearMonth);

        // DAO 객체 DTO 로 반환
        return createDiarysDTOBean.exec(diaryDAOs);
    }
}
