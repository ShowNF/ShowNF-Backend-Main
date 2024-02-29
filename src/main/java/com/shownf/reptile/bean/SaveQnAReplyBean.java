package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnAReplyBean {

    CreateUniqueIdBean createUniqueIdBean;
    CreateQnAReplyDAOBean createReplyDAOBean;
    GetQnACommentDAOBean getCommentDAOBean;
    UpdateQnACommentDAOBean updateQnACommentDAOBean;
    GetQnAPostDAOBean getPostDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnAReplyDAOBean saveQnAReplyDAOBean;
    SaveQnACommentDAOBean saveQnACommentDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveQnAReplyBean(CreateUniqueIdBean createUniqueIdBean, CreateQnAReplyDAOBean createReplyDAOBean, GetQnACommentDAOBean getCommentDAOBean, UpdateQnACommentDAOBean updateQnACommentDAOBean, GetQnAPostDAOBean getPostDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnAReplyDAOBean saveQnAReplyDAOBean, SaveQnACommentDAOBean saveQnACommentDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createReplyDAOBean = createReplyDAOBean;
        this.getCommentDAOBean = getCommentDAOBean;
        this.updateQnACommentDAOBean = updateQnACommentDAOBean;
        this.getPostDAOBean = getPostDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.updateUserSendCommentCountDAOBean = updateUserSendCommentCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveQnAReplyDAOBean = saveQnAReplyDAOBean;
        this.saveQnACommentDAOBean = saveQnACommentDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 대댓글 저장
    public Long exec(RequestQnAReplySaveDTO requestQnAReplySaveDTO){

        // QnA replyId 생성
        Long qnaReplyId = createUniqueIdBean.exec();

        // QnA 대댓글 객체 생성
        QnAReplyDAO qnaReplyDAO = createReplyDAOBean.exec(qnaReplyId, requestQnAReplySaveDTO);

        // 대댓글 작성 댓글 객체 가져오기
        QnACommentDAO qnACommentDAO = getCommentDAOBean.exec(requestQnAReplySaveDTO.getQnaCommentId());
        if (qnACommentDAO == null) return 0L;

        // 대댓글 저장에 따른 댓글 대댓글 갯수 추가
        updateQnACommentDAOBean.exec(qnACommentDAO, requestQnAReplySaveDTO);

        // 대댓글 작성 게시물 객체 가져오기
        QnAPostDAO qnAPostDAO = getPostDAOBean.exec(qnACommentDAO.getQnaPostId());
        if (qnAPostDAO == null) return 0L;

        // 대댓글 저장에 따른 게시물 댓글 갯수 추가
        updateQnAPostDAOBean.exec(qnACommentDAO, qnAPostDAO);

        // 게시물 작성한 유저 객체 가져오기
        UserDAO userDAO = getUserDAOBean.exec(qnAPostDAO.getUserId());
        if (userDAO == null) return 0L;

        // 대댓글 저장시 댓글 작성한 유저 댓글수 증가
        updateUserCommentCountDAOBean.exec(userDAO);

        // 대댓글 작성한 유저
        UserDAO writeUserDAO = getUserDAOBean.exec(requestQnAReplySaveDTO.getUserId());
        if (writeUserDAO == null) return 0L;

        // 대댓글 작성한 유저 send comment count 증가
        updateUserSendCommentCountDAOBean.exec(requestQnAReplySaveDTO, writeUserDAO);

        // 경험치 추가
        updateUserExpDAOBean.exec(requestQnAReplySaveDTO, writeUserDAO);

        // 대댓글 저장
        saveQnAReplyDAOBean.exec(qnaReplyDAO);

        // 댓글 저장
        saveQnACommentDAOBean.exec(qnACommentDAO);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnAPostDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeUserDAO);

        // 대댓글 replyId 반환
        return qnaReplyId;
    }
}
