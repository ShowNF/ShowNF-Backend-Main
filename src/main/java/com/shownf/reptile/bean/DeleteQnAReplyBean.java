package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteQnAReplyBean {

    GetQnAReplyDAOBean getQnAReplyDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    GetQnACommentDAOBean getQnACommentDAOBean;
    UpdateQnACommentDAOBean updateQnACommentDAOBean;
    GetQnAPostDAOBean getQnAPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnAReplyDAOBean saveReplyDAOBean;
    SaveQnACommentDAOBean saveCommentDAOBean;
    SaveQnAPostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteQnAReplyBean(GetQnAReplyDAOBean getQnAReplyDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, GetQnACommentDAOBean getQnACommentDAOBean, UpdateQnACommentDAOBean updateQnACommentDAOBean, GetQnAPostDAOBean getQnAPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnAReplyDAOBean saveReplyDAOBean, SaveQnACommentDAOBean saveCommentDAOBean, SaveQnAPostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getQnAReplyDAOBean = getQnAReplyDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.getQnACommentDAOBean = getQnACommentDAOBean;
        this.updateQnACommentDAOBean = updateQnACommentDAOBean;
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.updateUserSendCommentCountDAOBean = updateUserSendCommentCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 대댓글 삭제
    public Long exec(RequestQnAReplyDeleteDTO requestQnAReplyDeleteDTO, HttpServletRequest request){

        // 아이디로 삭제할 QnA 대댓글 찾기
        QnAReplyDAO qnAReplyDAO = getQnAReplyDAOBean.exec(requestQnAReplyDeleteDTO.getQnaReplyId());
        if (qnAReplyDAO == null) return 0L;

        // 유저 댓글 토큰으로 확인
        UserDAO writeUserDAO = getUserDAOBean.exec(qnAReplyDAO.getUserId());
        if (writeUserDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(writeUserDAO, request))
            return null;

        // 대댓글 deleteCheck 값 true 설정
        qnAReplyDAO.setDeleteCheck(true);

        // 대댓글 작성된 댓글 객체 가져오기
        QnACommentDAO qnACommentDAO = getQnACommentDAOBean.exec(qnAReplyDAO.getQnaCommentId());
        if (qnACommentDAO == null) return 0L;

        // 댓글 대댓글 갯수 감소
        updateQnACommentDAOBean.exec(0L, qnACommentDAO, requestQnAReplyDeleteDTO);

        // 댓글 작성된 게시물 객체 가져오기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(qnACommentDAO.getQnaPostId());
        if (qnAPostDAO == null) return 0L;

        // QnA 게시물 댓글 감소
        updateQnAPostDAOBean.exec(0L, qnACommentDAO, qnAPostDAO);

        // QnA 게시물 작성한 유저 가져오기
        UserDAO userDAO = getUserDAOBean.exec(qnAPostDAO.getUserId());
        if (userDAO == null) return 0L;

        // 대댓글 삭제시 유저 댓글수 감소
        updateUserCommentCountDAOBean.exec(0L, userDAO);

        // 대댓글 작성한 유저 send comment count 감소
        updateUserSendCommentCountDAOBean.exec(requestQnAReplyDeleteDTO, userDAO);

        // 경험치 감소
        updateUserExpDAOBean.exec(requestQnAReplyDeleteDTO, userDAO);

        // QnA 대댓글 저장
        saveReplyDAOBean.exec(qnAReplyDAO);

        // QnA 댓글 저장
        saveCommentDAOBean.exec(qnACommentDAO);

        // QnA 게시물 저장
        savePostDAOBean.exec(qnAPostDAO);

        // QnA 유저저장
        saveUserDAOBean.exec(writeUserDAO);
        saveUserDAOBean.exec(userDAO);

        // replyId 반환
        return qnAReplyDAO.getQnaReplyId();
    }
}
