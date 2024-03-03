package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteQnACommentHeartBean {

    GetQnACommentHeartDAOBean getCommentHeartDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    GetQnACommentDAOBean getQnACommentDAOBean;
    UpdateQnACommentDAOBean updateQnACommentDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    DeleteQnACommentHeartDAOBean deleteQnACommentHeartDAOBean;
    SaveQnACommentDAOBean saveQnACommentDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteQnACommentHeartBean(GetQnACommentHeartDAOBean getCommentHeartDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, GetQnACommentDAOBean getQnACommentDAOBean, UpdateQnACommentDAOBean updateQnACommentDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, DeleteQnACommentHeartDAOBean deleteQnACommentHeartDAOBean, SaveQnACommentDAOBean saveQnACommentDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.getQnACommentDAOBean = getQnACommentDAOBean;
        this.updateQnACommentDAOBean = updateQnACommentDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.deleteQnACommentHeartDAOBean = deleteQnACommentHeartDAOBean;
        this.saveQnACommentDAOBean = saveQnACommentDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // QnA 댓글 좋아요 삭제
    public Long exec(RequestQnACommentHeartDeleteDTO requestQnACommentHeartDeleteDTO, HttpServletRequest request){

        // 아이디로 삭제할 좋아요 찾기
        QnACommentHeartDAO qnACommentHeartDAO = getCommentHeartDAOBean.exec(requestQnACommentHeartDeleteDTO.getQnaCommentId(), requestQnACommentHeartDeleteDTO.getUserId());

        // 취소 중복 배제
        if (qnACommentHeartDAO == null)
            return 0L;

        // 유저 토큰 확인
        UserDAO writeuserDAO = getUserDAOBean.exec(requestQnACommentHeartDeleteDTO.getUserId());
        if (writeuserDAO == null) return 0L;
        if (!checkUserAccessTokenDAOBean.exec(writeuserDAO, request))
            return 0L;

        // 좋아요 취소된 QnA 댓글 가져오기
        QnACommentDAO qnACommentDAO = getQnACommentDAOBean.exec(requestQnACommentHeartDeleteDTO.getQnaCommentId());
        if (qnACommentDAO == null) return 0L;

        // 댓글 좋아요 갯수 감소
        updateQnACommentDAOBean.exec(null, qnACommentDAO);

        // 좋아요 sender, receiver 감소
        UserDAO userDAO = getUserDAOBean.exec(qnACommentDAO.getUserId());
        if (userDAO == null) return 0L;
        updateUserReceiveHeartDAOBean.exec(null, userDAO);

        updateUserSendHeartDAOBean.exec(0L, userDAO);

        // 경험치 삭제
        writeuserDAO = updateUserExpDAOBean.exec(null, qnACommentHeartDAO, userDAO);

        // 좋아요 삭제
        deleteQnACommentHeartDAOBean.exec(qnACommentHeartDAO);

        // 댓글 저장
        saveQnACommentDAOBean.exec(qnACommentDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeuserDAO);

        // commentHeartId 반환
        return qnACommentHeartDAO.getQnaCommentHeartId();
    }
}
