package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDiaryRecentDAOBean {

    DiaryRepositoryJPA diaryRepositoryJPA;

    @Autowired
    public GetDiaryRecentDAOBean(DiaryRepositoryJPA diaryRepositoryJPA) {
        this.diaryRepositoryJPA = diaryRepositoryJPA;
    }

    // 가장 최근 다이어리 가져오기
    public DiaryDAO exec(Long petId){
        return diaryRepositoryJPA.findFirstByPetIdOrderByUploadTimeDesc(petId);
    }
}
