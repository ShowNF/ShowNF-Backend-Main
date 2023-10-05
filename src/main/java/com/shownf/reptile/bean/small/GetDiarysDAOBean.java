package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetDiarysDAOBean {

    DiaryRepositoryJPA diaryRepositoryJPA;

    @Autowired
    public GetDiarysDAOBean(DiaryRepositoryJPA diaryRepositoryJPA) {
        this.diaryRepositoryJPA = diaryRepositoryJPA;
    }

    // 펫아이디로 월별 다이어리 조회
    public List<DiaryDAO> exec(Long petId, String yearMonth){
        List<DiaryDAO> diaryDAOs = diaryRepositoryJPA.findByPetId(petId);
        List<DiaryDAO> newDiaryDAOs = new ArrayList<>();

        for(DiaryDAO diaryDAO : diaryDAOs){
            if (diaryDAO.getMonth().equals(yearMonth))
                newDiaryDAOs.add(diaryDAO);
        }
        return newDiaryDAOs;
    }
}
