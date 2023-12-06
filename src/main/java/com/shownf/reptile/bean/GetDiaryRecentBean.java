package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestDiaryDTO;
import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.bean.small.CreateDiaryDTOBean;
import com.shownf.reptile.bean.small.GetDiaryRecentDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDiaryRecentBean {

    GetDiaryRecentDAOBean getDiaryRecentDAOBean;
    CreateDiaryDTOBean createDiaryDTOBean;

    @Autowired
    public GetDiaryRecentBean(GetDiaryRecentDAOBean getDiaryRecentDAOBean, CreateDiaryDTOBean createDiaryDTOBean) {
        this.getDiaryRecentDAOBean = getDiaryRecentDAOBean;
        this.createDiaryDTOBean = createDiaryDTOBean;
    }

    // 가장 최근 다이어리 조회
    public RequestDiaryDTO exec(Long petId){

        // 펫 아이디로 가장 최근 다이어리 가져오기
        DiaryDAO diaryDAO = getDiaryRecentDAOBean.exec(petId);

        // DAO DTO 변경 후 반환
        return createDiaryDTOBean.exec(diaryDAO);
    }
}
