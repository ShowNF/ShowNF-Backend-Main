package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.bean.SaveQnAPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAPostService {

    SaveQnAPostBean saveQnAPostBean;

    @Autowired
    public QnAPostService(SaveQnAPostBean saveQnAPostBean) {
        this.saveQnAPostBean = saveQnAPostBean;
    }

    // QnA 게시물 저장
    public Long saveQnAPostDAO(RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        return saveQnAPostBean.exec(requestQnAPostSaveDTO);
    }

}
