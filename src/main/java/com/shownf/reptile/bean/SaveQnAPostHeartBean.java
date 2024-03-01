package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnAPostHeartBean {

    GetQnAPostHeartDAOBean getQnAPostHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreateQnAPostHeartDAOBean createQnAPostHeartDAOBean;
    GetQnAPostDAOBean getQnAPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnAPostHeartDAOBean savePostHeartDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    UpdateQnAPostMetaDAOBean updateQnAPostMetaDAOBean;
    SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean;

    @Autowired
    public SaveQnAPostHeartBean(GetQnAPostHeartDAOBean getQnAPostHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateQnAPostHeartDAOBean createQnAPostHeartDAOBean, GetQnAPostDAOBean getQnAPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnAPostHeartDAOBean savePostHeartDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveUserDAOBean saveUserDAOBean, GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, UpdateQnAPostMetaDAOBean updateQnAPostMetaDAOBean, SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean) {
        this.getQnAPostHeartDAOBean = getQnAPostHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createQnAPostHeartDAOBean = createQnAPostHeartDAOBean;
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.savePostHeartDAOBean = savePostHeartDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.updateQnAPostMetaDAOBean = updateQnAPostMetaDAOBean;
        this.saveQnAPostMetaDAOBean = saveQnAPostMetaDAOBean;
    }

    // QnA 게시물 좋아요 저장
    public Long exec(RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){

        // QnA 게시물 좋아요 중복 배제
        if (getQnAPostHeartDAOBean.exec(requestQnAPostHeartSaveDTO) != null)
            return 0L;

        // postHeartId 생성
        Long qnaPostHeartId = createUniqueIdBean.exec();

        // 게시물 좋아요 객체 생성
        QnAPostHeartDAO qnAPostHeartDAO = createQnAPostHeartDAOBean.exec(qnaPostHeartId, requestQnAPostHeartSaveDTO);

        // QnA 게시물 객체 찾기
        QnAPostDAO qnaPostDAO = getQnAPostDAOBean.exec(requestQnAPostHeartSaveDTO.getQnaPostId());
        if (qnaPostDAO == null) return 0L;

        // 게시물의 좋아요 갯수 추가
        updateQnAPostDAOBean.exec(qnAPostHeartDAO, qnaPostDAO);

        // 좋아요 receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(qnaPostDAO);
        if (userDAO1 == null) return 0L;

        // 좋아요 sender 추가
        UserDAO userDAO2;
        if (requestQnAPostHeartSaveDTO.getUserId().equals(userDAO1.getUserId()))
            userDAO2 = updateUserSendHeartDAOBean.exec(qnAPostHeartDAO, userDAO1);
        else userDAO2 = updateUserSendHeartDAOBean.exec(qnAPostHeartDAO);
        if (userDAO2 == null) return 0L;

        // 유저 경험치 추가
        userDAO2 = updateUserExpDAOBean.exec(qnAPostHeartDAO, userDAO2);

        // QnA 게시물 메타데이터 수정
        QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(requestQnAPostHeartSaveDTO.getQnaPostId());
        if (qnAPostMeta == null) return 0L;
        updateQnAPostMetaDAOBean.exec(qnAPostHeartDAO, qnAPostMeta);

        // 좋아요 저장
        savePostHeartDAOBean.exec(qnAPostHeartDAO);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnaPostDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // QnA 게시물 메타데이터 저장
        saveQnAPostMetaDAOBean.exec(qnAPostMeta);

        // postHeartId 반환
        return qnaPostHeartId;
    }
}
