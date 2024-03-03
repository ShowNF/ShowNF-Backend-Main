package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnAReplyHeartBean {

    GetQnAReplyHeartDAOBean getQnAReplyHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreateQnAReplyHeartDAOBean createQnAReplyHeartDAOBean;
    GetQnAReplyDAOBean getQnAReplyDAOBean;
    UpdateQnAReplyDAOBean updateQnAReplyDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnAReplyHeartDAOBean saveQnAReplyHeartDAOBean;
    SaveQnAReplyDAOBean saveQnAReplyDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveQnAReplyHeartBean(GetQnAReplyHeartDAOBean getQnAReplyHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateQnAReplyHeartDAOBean createQnAReplyHeartDAOBean, GetQnAReplyDAOBean getQnAReplyDAOBean, UpdateQnAReplyDAOBean updateQnAReplyDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnAReplyHeartDAOBean saveQnAReplyHeartDAOBean, SaveQnAReplyDAOBean saveQnAReplyDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getQnAReplyHeartDAOBean = getQnAReplyHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createQnAReplyHeartDAOBean = createQnAReplyHeartDAOBean;
        this.getQnAReplyDAOBean = getQnAReplyDAOBean;
        this.updateQnAReplyDAOBean = updateQnAReplyDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveQnAReplyHeartDAOBean = saveQnAReplyHeartDAOBean;
        this.saveQnAReplyDAOBean = saveQnAReplyDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // QnA 대댓글 좋아요 저장
    public Long exec(RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){

        // QnA 대댓글 좋아요 중복 배제
        if (getQnAReplyHeartDAOBean.exec(requestQnAReplyHeartSaveDTO.getQnaReplyId(), requestQnAReplyHeartSaveDTO.getUserId()) != null)
            return 0L;

        // QnA replyHeartId 생성
        Long qnaReplyHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        QnAReplyHeartDAO qnAReplyHeartDAO = createQnAReplyHeartDAOBean.exec(qnaReplyHeartId, requestQnAReplyHeartSaveDTO);

        // 대댓글 좋아요 갯수 추가
        QnAReplyDAO qnAReplyDAO = getQnAReplyDAOBean.exec(requestQnAReplyHeartSaveDTO.getQnaReplyId());
        if (qnAReplyDAO == null) return 0L;
        updateQnAReplyDAOBean.exec(qnAReplyDAO, requestQnAReplyHeartSaveDTO);

        // 좋아요 sender, receiver 추가
        UserDAO userDAO = getUserDAOBean.exec(qnAReplyDAO.getUserId());
        if (userDAO == null) return 0L;
        updateUserReceiveHeartDAOBean.exec(userDAO);

        UserDAO writeUserDAO;
        // 좋아요 sender 추가
        if (userDAO.getUserId().equals(requestQnAReplyHeartSaveDTO.getUserId()))
            writeUserDAO = userDAO;
        else {
            writeUserDAO = getUserDAOBean.exec(requestQnAReplyHeartSaveDTO.getUserId());
            if (writeUserDAO == null) return 0L;
        }
        updateUserSendHeartDAOBean.exec(writeUserDAO);

        // 경험치 추가
        updateUserExpDAOBean.exec(qnAReplyHeartDAO, writeUserDAO);

        // 대댓글 좋아요 저장
        saveQnAReplyHeartDAOBean.exec(qnAReplyHeartDAO);

        // 대댓글 저장
        saveQnAReplyDAOBean.exec(qnAReplyDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeUserDAO);

        // replyHeartId 반환
        return qnaReplyHeartId;
    }
}
