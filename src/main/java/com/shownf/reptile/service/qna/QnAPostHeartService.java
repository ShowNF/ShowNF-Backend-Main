package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.bean.DeleteQnAPostHeartBean;
import com.shownf.reptile.bean.SaveQnAPostHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAPostHeartService {

    SaveQnAPostHeartBean saveQnAPostHeartBean;
    DeleteQnAPostHeartBean deleteQnAPostHeartBean;

    @Autowired
    public QnAPostHeartService(SaveQnAPostHeartBean saveQnAPostHeartBean, DeleteQnAPostHeartBean deleteQnAPostHeartBean) {
        this.saveQnAPostHeartBean = saveQnAPostHeartBean;
        this.deleteQnAPostHeartBean = deleteQnAPostHeartBean;
    }

    // QnA 게시물 좋아요 저장
    public Long saveQnAPostHeart(RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){
        return saveQnAPostHeartBean.exec(requestQnAPostHeartSaveDTO);
    }

    // QnA 게시물 좋아요 삭제
    public Long deleteQnAPostHeart(RequestQnAPostHeartDeleteDTO requestQnAPostHeartSaveDTO){
        return deleteQnAPostHeartBean.exec(requestQnAPostHeartSaveDTO);
    }
}