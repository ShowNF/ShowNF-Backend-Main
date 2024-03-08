package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostBean {

    GetQnAPostDAOBean getQnAPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    CreateQnAPostDTOBean createQnAPostDTOBean;
    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    UpdateQnAPostMetaDAOBean updatePostViewCountDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean;
    SavePostLogDAOBean savePostLogDAOBean;

    @Autowired
    public GetQnAPostBean(GetQnAPostDAOBean getQnAPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, CreateQnAPostDTOBean createQnAPostDTOBean, GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, UpdateQnAPostMetaDAOBean updatePostViewCountDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean, SavePostLogDAOBean savePostLogDAOBean) {
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.createQnAPostDTOBean = createQnAPostDTOBean;
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.updatePostViewCountDAOBean = updatePostViewCountDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveQnAPostMetaDAOBean = saveQnAPostMetaDAOBean;
        this.savePostLogDAOBean = savePostLogDAOBean;
    }

    // QnA 게시물 조회
    public ResponseQnAPostGetDTO exec(Long qnaPostId, Long userId){

        // qnaPostId 로 QnA 게시물 찾기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(qnaPostId);
        if (qnAPostDAO == null) return null;

        // 게시물 찾기로 인한 조회수 1 증가
        updateQnAPostDAOBean.exec(qnAPostDAO);

        // DTO 에 게시물 객체 넘기기
        ResponseQnAPostGetDTO responseQnAPostGetDTO = createQnAPostDTOBean.exec(qnAPostDAO);

        // postMeta 게시물 찾기
        QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(qnaPostId);
        if (qnAPostMeta == null) return null;

        // QnA 게시물 메타데이터 조회수 증가
        updatePostViewCountDAOBean.exec(qnAPostMeta);

        // QnA 게시물 저장
        saveQnAPostDAOBean.exec(qnAPostDAO);

        // QnA 게시물 메타데이터 저장
        saveQnAPostMetaDAOBean.exec(qnAPostMeta);

        // 최근 조회 QnA 게시물 추가
        savePostLogDAOBean.exec(qnaPostId, userId, 1);

        // DTO 반환
        return responseQnAPostGetDTO;
    }
}
