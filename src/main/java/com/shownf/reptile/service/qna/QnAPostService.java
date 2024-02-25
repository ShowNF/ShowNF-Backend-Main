package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.bean.GetQnAPostBean;
import com.shownf.reptile.bean.SaveQnAPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAPostService {

    GetQnAPostBean getQnAPostBean;
    SaveQnAPostBean saveQnAPostBean;


    @Autowired
    public QnAPostService(GetQnAPostBean getQnAPostBean, SaveQnAPostBean saveQnAPostBean) {
        this.getQnAPostBean = getQnAPostBean;
        this.saveQnAPostBean = saveQnAPostBean;
    }

    // QnA 게시물 조회
    public ResponseQnAPostGetDTO getQnAPostDAO(Long qnaPostId){
        return getQnAPostBean.exec(qnaPostId);
    }

    // QnA 게시물 저장
    public Long saveQnAPostDAO(RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        return saveQnAPostBean.exec(requestQnAPostSaveDTO);
    }

}
