package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.CreateQnAPostDTOBean;
import com.shownf.reptile.bean.small.GetQnAPostDAOBean;
import com.shownf.reptile.bean.small.SaveQnAPostDAOBean;
import com.shownf.reptile.bean.small.UpdateQnAPostDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostBean {

    GetQnAPostDAOBean getQnAPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    CreateQnAPostDTOBean createQnAPostDTOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;

    @Autowired
    public GetQnAPostBean(GetQnAPostDAOBean getQnAPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, CreateQnAPostDTOBean createQnAPostDTOBean, SaveQnAPostDAOBean saveQnAPostDAOBean) {
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.createQnAPostDTOBean = createQnAPostDTOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
    }

    // QnA 게시물 조회
    public ResponseQnAPostGetDTO exec(Long qnaPostId){

        // qnaPostId 로 QnA 게시물 찾기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(qnaPostId);
        if (qnAPostDAO == null) return null;

        // 게시물 찾기로 인한 조회수 1 증가
        updateQnAPostDAOBean.exec(qnAPostDAO);

        // DTO 에 게시물 객체 넘기기
        ResponseQnAPostGetDTO responseQnAPostGetDTO = createQnAPostDTOBean.exec(qnAPostDAO);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnAPostDAO);

        // DTO 반환
        return responseQnAPostGetDTO;
    }
}
