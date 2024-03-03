package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnACommentHeartBean {

    GetQnACommentHeartDAOBean getCommentHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreateQnACommentHeartDAOBean createQnACommentHeartDAOBean;
    GetQnACommentDAOBean getQnACommentDAOBean;
    UpdateQnACommentDAOBean updateQnACommentDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    SaveQnACommentDAOBean saveCommentDAOBean;
    SaveQnACommentHeartDAOBean saveCommentHeartDAOBean;

    @Autowired
    public SaveQnACommentHeartBean(GetQnACommentHeartDAOBean getCommentHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateQnACommentHeartDAOBean createQnACommentHeartDAOBean, GetQnACommentDAOBean getQnACommentDAOBean, UpdateQnACommentDAOBean updateQnACommentDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveUserDAOBean saveUserDAOBean, SaveQnACommentDAOBean saveCommentDAOBean, SaveQnACommentHeartDAOBean saveCommentHeartDAOBean) {
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createQnACommentHeartDAOBean = createQnACommentHeartDAOBean;
        this.getQnACommentDAOBean = getQnACommentDAOBean;
        this.updateQnACommentDAOBean = updateQnACommentDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
    }


    // QnA 댓글 좋아요 저장
    public Long exec(RequestQnACommentHeartSaveDTO requestQnACommentHeartSaveDTO) {

        // QnA 댓글 좋아요 중복 배제
        if (getCommentHeartDAOBean.exec(requestQnACommentHeartSaveDTO.getQnaCommentId(), requestQnACommentHeartSaveDTO.getUserId()) != null)
            return 0L;

        // QnA commentHeartId 생성
        Long qnaCommentHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        QnACommentHeartDAO qnACommentHeartDAO = createQnACommentHeartDAOBean.exec(qnaCommentHeartId, requestQnACommentHeartSaveDTO);
        if (qnACommentHeartDAO == null) return 0L;

        // 댓글 좋아요 갯수 추가
        QnACommentDAO qnACommentDAO = getQnACommentDAOBean.exec(requestQnACommentHeartSaveDTO.getQnaCommentId());
        if (qnACommentDAO == null) return 0L;
        updateQnACommentDAOBean.exec(qnACommentDAO, requestQnACommentHeartSaveDTO);

        // 좋아요 receiver 추가
        UserDAO userDAO = getUserDAOBean.exec(qnACommentDAO.getUserId());
        if (userDAO == null) return 0L;
        updateUserReceiveHeartDAOBean.exec(userDAO);

        UserDAO writeUserDAO;
        // 좋아요 sender 추가
        if (userDAO.getUserId().equals(requestQnACommentHeartSaveDTO.getUserId())) {
            writeUserDAO = userDAO;
            updateUserSendHeartDAOBean.exec(writeUserDAO, requestQnACommentHeartSaveDTO);
        }
        else {
            writeUserDAO = getUserDAOBean.exec(requestQnACommentHeartSaveDTO.getUserId());
            if (writeUserDAO == null) return 0L;
            updateUserSendHeartDAOBean.exec(writeUserDAO, requestQnACommentHeartSaveDTO);
        }

        // 유저 경험치 추가
        updateUserExpDAOBean.exec(qnACommentHeartDAO, writeUserDAO);

        // 댓글 좋아요 저장
        saveCommentHeartDAOBean.exec(qnACommentHeartDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(qnACommentDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeUserDAO);

        // commentHeartId 반환
        return qnaCommentHeartId;
    }
}
