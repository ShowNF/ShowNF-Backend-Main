package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteQnAPostHeartBean {

    GetQnAPostHeartDAOBean getPostHeartDAOBean;
    GetQnAPostDAOBean getQnAPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    DeleteQnAPostHeartDAOBean deleteQnAPostHeartDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteQnAPostHeartBean(GetQnAPostHeartDAOBean getPostHeartDAOBean, GetQnAPostDAOBean getQnAPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, DeleteQnAPostHeartDAOBean deleteQnAPostHeartDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPostHeartDAOBean = getPostHeartDAOBean;
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.deleteQnAPostHeartDAOBean = deleteQnAPostHeartDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // QnA 게시물 좋아요 삭제
    public Long exec(RequestQnAPostHeartDeleteDTO requestQnAPostHeartDeleteDTO){

        // QnA 게시물 아이디와 유저 아이디로 객체 찾기
        QnAPostHeartDAO qnAPostHeartDAO = getPostHeartDAOBean.exec(requestQnAPostHeartDeleteDTO);

        // 취소 중복 배제
        if (qnAPostHeartDAO == null)
            return 0L;
/*
        // 좋아요 해당하는 게시물 확인
        if (!checkPostIdPostDAOBean.exec(qnAPostHeartDAO, requestQnAPostHeartDeleteDTO))
            return null;

        // 좋아요 해당하는 유저 확인
        if (!checkUserIdPostDAOBean.exec(qnAPostHeartDAO, requestQnAPostHeartDeleteDTO))
            return null;*/

        // QnA 게시물 객체 찾기
        QnAPostDAO qnaPostDAO = getQnAPostDAOBean.exec(qnAPostHeartDAO.getQnaPostId());
        if (qnaPostDAO == null) return 0L;

        // QnA 게시물 좋아요 갯수 감소
        updateQnAPostDAOBean.exec(null, qnAPostHeartDAO, qnaPostDAO);

        // 좋아요 sender, receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(null, qnaPostDAO);
        if (userDAO1 == null) return 0L;

        UserDAO userDAO2;
        if (requestQnAPostHeartDeleteDTO.getUserId().equals(userDAO1.getUserId()))
            userDAO2 = updateUserSendHeartDAOBean.exec(null, qnAPostHeartDAO, userDAO1);
        else userDAO2 = updateUserSendHeartDAOBean.exec(null, qnAPostHeartDAO);
        if (userDAO2 == null) return 0L;

        // 경험치 삭제
        userDAO2 = updateUserExpDAOBean.exec(null, qnAPostHeartDAO, userDAO2);


        // 좋아요 삭제
        deleteQnAPostHeartDAOBean.exec(qnAPostHeartDAO);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnaPostDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // postHeartId 반환
        return qnAPostHeartDAO.getQnaPostHeartId();
    }
}
