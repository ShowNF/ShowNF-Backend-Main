package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentDeleteDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteQnACommentBean {

    GetQnACommentDAOBean getQnACommentDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    GetQnAPostDAOBean getQnAPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnACommentDAOBean saveQnACommentDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    UpdateQnAPostMetaDAOBean updateQnAPostMetaDAOBean;
    SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean;

    @Autowired
    public DeleteQnACommentBean(GetQnACommentDAOBean getQnACommentDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, GetQnAPostDAOBean getQnAPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnACommentDAOBean saveQnACommentDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveUserDAOBean saveUserDAOBean, GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, UpdateQnAPostMetaDAOBean updateQnAPostMetaDAOBean, SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean) {
        this.getQnACommentDAOBean = getQnACommentDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.updateUserSendCommentCountDAOBean = updateUserSendCommentCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveQnACommentDAOBean = saveQnACommentDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.updateQnAPostMetaDAOBean = updateQnAPostMetaDAOBean;
        this.saveQnAPostMetaDAOBean = saveQnAPostMetaDAOBean;
    }


    // QnA 댓글 삭제
    public Long exec(RequestQnACommentDeleteDTO requestQnACommentDeleteDTO, HttpServletRequest request){

        // QnA 댓글 아이디로 삭제할 댓글 찾기
        QnACommentDAO qnACommentDAO = getQnACommentDAOBean.exec(requestQnACommentDeleteDTO.getQnaCommentId());

        // 유저 댓글 토큰으로 확인
        UserDAO writeUserDAO = getUserDAOBean.exec(requestQnACommentDeleteDTO.getUserId());
        if (writeUserDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(writeUserDAO, request))
            return null;

        // 댓글 deleteCheck 값 true 변경
        qnACommentDAO.setDeleteCheck(true);

        // 댓글 해당하는 게시물 가져오기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(qnACommentDAO.getQnaPostId());
        if (qnAPostDAO == null) return 0L;

        // 게시물 댓글 갯수 감소
        updateQnAPostDAOBean.exec(0L, qnACommentDAO, qnAPostDAO);

        // 게시물 작성한 유저 가져오기
        UserDAO userDAO = getUserDAOBean.exec(qnAPostDAO.getUserId());
        if (userDAO == null) return 0L;

        // 댓글 삭제시 게시물 작성한 유저 댓글수 감소
        updateUserCommentCountDAOBean.exec(0L,userDAO);

        // 댓글 삭제한 유저 send comment count 감소
        updateUserSendCommentCountDAOBean.exec(requestQnACommentDeleteDTO, writeUserDAO);

        // 경험치 삭제
        updateUserExpDAOBean.exec(requestQnACommentDeleteDTO, writeUserDAO);

        // QnA 게시물 메타데이터 가져오기
        QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(qnAPostDAO.getQnaPostId());
        if (qnAPostMeta == null) return 0L;
        updateQnAPostMetaDAOBean.exec(qnACommentDAO, qnAPostDAO, qnAPostMeta);

        // 댓글 저장
        saveQnACommentDAOBean.exec(qnACommentDAO);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnAPostDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeUserDAO);

        // QnA 게시물 메타데이터 저장
        saveQnAPostMetaDAOBean.exec(qnAPostMeta);

        // commentId 반환
        return qnACommentDAO.getQnaCommentId();
    }
}
