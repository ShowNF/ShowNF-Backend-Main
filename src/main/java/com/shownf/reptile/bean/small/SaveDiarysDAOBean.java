package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.DiaryDAO;
import com.shownf.reptile.repository.DiaryRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaveDiarysDAOBean {

    DiaryRepositoryJPA diaryRepositoryJPA;

    @Autowired
    public SaveDiarysDAOBean(DiaryRepositoryJPA diaryRepositoryJPA) {
        this.diaryRepositoryJPA = diaryRepositoryJPA;
    }

    // 다이어리들 저장
    public void exec(List<DiaryDAO> diaryDAOS){
        for (DiaryDAO diaryDAO : diaryDAOS)
            diaryRepositoryJPA.save(diaryDAO);
    }
}
