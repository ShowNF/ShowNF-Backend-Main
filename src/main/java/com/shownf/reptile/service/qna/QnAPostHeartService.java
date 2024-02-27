package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.bean.SaveQnAPostHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAPostHeartService {

    SaveQnAPostHeartBean saveQnAPostHeartBean;

    @Autowired
    public QnAPostHeartService(SaveQnAPostHeartBean saveQnAPostHeartBean) {
        this.saveQnAPostHeartBean = saveQnAPostHeartBean;
    }

    // QnA 게시물 좋아요 저장
    public Long saveQnAPostHeart(RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){
        return saveQnAPostHeartBean.exec(requestQnAPostHeartSaveDTO);
    }
}