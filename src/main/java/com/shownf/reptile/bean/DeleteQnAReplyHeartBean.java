package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartDeleteDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteQnAReplyHeartBean {

    GetQnAReplyHeartDAOBean getReplyHeartDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    GetQnAReplyDAOBean getQnAReplyDAOBean;
    UpdateQnAReplyDAOBean updateQnAReplyDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    DeleteQnAReplyHeartDAOBean deleteQnAReplyHeartDAOBean;
    SaveQnAReplyDAOBean saveQnAReplyDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteQnAReplyHeartBean(GetQnAReplyHeartDAOBean getReplyHeartDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, GetQnAReplyDAOBean getQnAReplyDAOBean, UpdateQnAReplyDAOBean updateQnAReplyDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, DeleteQnAReplyHeartDAOBean deleteQnAReplyHeartDAOBean, SaveQnAReplyDAOBean saveQnAReplyDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getReplyHeartDAOBean = getReplyHeartDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.getQnAReplyDAOBean = getQnAReplyDAOBean;
        this.updateQnAReplyDAOBean = updateQnAReplyDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.deleteQnAReplyHeartDAOBean = deleteQnAReplyHeartDAOBean;
        this.saveQnAReplyDAOBean = saveQnAReplyDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // QnA 대댓글 좋아요 삭제
    public Long exec(RequestQnAReplyHeartDeleteDTO requestQnAReplyHeartDeleteDTO, HttpServletRequest request){

        // 댓글과 유저 아이디로 삭제할 좋아요 찾기
        QnAReplyHeartDAO qnAReplyHeartDAO = getReplyHeartDAOBean.exec(requestQnAReplyHeartDeleteDTO.getQnaReplyId(), requestQnAReplyHeartDeleteDTO.getUserId());

        // 취소 중복 배제
        if (qnAReplyHeartDAO == null)
            return 0L;

        // 유저 확인
        UserDAO writeUserDAO = getUserDAOBean.exec(requestQnAReplyHeartDeleteDTO.getUserId());
        if (writeUserDAO == null) return 0L;
        if (!checkUserAccessTokenDAOBean.exec(writeUserDAO, request))
            return 0L;

        // 좋아요 취소된 QnA 대댓글 가져오기
        QnAReplyDAO qnAReplyDAO = getQnAReplyDAOBean.exec(requestQnAReplyHeartDeleteDTO.getQnaReplyId());
        if (qnAReplyDAO == null) return 0L;

        // QnA 대댓글 좋아요 갯수 감소
        updateQnAReplyDAOBean.exec(qnAReplyDAO, requestQnAReplyHeartDeleteDTO);

        // 좋아요 sender, receiver 감소
        UserDAO userDAO = getUserDAOBean.exec(qnAReplyDAO.getUserId());
        if (userDAO == null) return 0L;
        updateUserReceiveHeartDAOBean.exec(null, userDAO);

        updateUserSendHeartDAOBean.exec(0L, writeUserDAO);

        // 경험치 추가
        updateUserExpDAOBean.exec(null, qnAReplyHeartDAO, writeUserDAO);

        // QnA 대댓글 삭제
        deleteQnAReplyHeartDAOBean.exec(qnAReplyHeartDAO);

        // QnA 대댓글 저장
        saveQnAReplyDAOBean.exec(qnAReplyDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeUserDAO);

        // replyHeartId 반환
        return qnAReplyHeartDAO.getQnaReplyHeartId();
    }
}
