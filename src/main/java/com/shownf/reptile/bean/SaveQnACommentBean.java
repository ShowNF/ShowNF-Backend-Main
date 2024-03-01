package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnACommentBean {

    CreateUniqueIdBean createUniqueIdBean;
    CreateQnACommentDAOBean createQnACommentDAOBean;
    GetQnAPostDAOBean getQnAPostDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnACommentDAOBean saveCommentDAOBean;
    SaveQnAPostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    UpdateQnAPostMetaDAOBean updateQnAPostMetaDAOBean;
    SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean;

    @Autowired
    public SaveQnACommentBean(CreateUniqueIdBean createUniqueIdBean, CreateQnACommentDAOBean createQnACommentDAOBean, GetQnAPostDAOBean getQnAPostDAOBean, GetUserDAOBean getUserDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnACommentDAOBean saveCommentDAOBean, SaveQnAPostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean, GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, UpdateQnAPostMetaDAOBean updateQnAPostMetaDAOBean, SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createQnACommentDAOBean = createQnACommentDAOBean;
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.updateUserSendCommentCountDAOBean = updateUserSendCommentCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.updateQnAPostMetaDAOBean = updateQnAPostMetaDAOBean;
        this.saveQnAPostMetaDAOBean = saveQnAPostMetaDAOBean;
    }

    // QnA 댓글 저장
    public Long exec(RequestQnACommentSaveDTO requestQnACommentSaveDTO){

        // QnA commentId 생성
        Long qnaCommentId = createUniqueIdBean.exec();

        // QnA 댓글 객체 생성
        QnACommentDAO qnaCommentDAO = createQnACommentDAOBean.exec(qnaCommentId, requestQnACommentSaveDTO);

        // QnA 게시물 찾기
        QnAPostDAO qnaPostDAO = getQnAPostDAOBean.exec(requestQnACommentSaveDTO.getQnaPostId());
        if (qnaPostDAO == null) return 0L;

        // QnA 게시물 작성 유저 찾기
        UserDAO userDAO = getUserDAOBean.exec(qnaPostDAO.getUserId());
        if (userDAO == null) return 0L;

        // 댓글 저장에 따른 게시물 댓글 갯수 추가
        updateQnAPostDAOBean.exec(qnaCommentDAO, qnaPostDAO);

        // 댓글 추가시 게시물 작성 유저 댓글 수 증가
        updateUserCommentCountDAOBean.exec(userDAO);

        // 댓글 작성한 유저
        UserDAO writeUserDAO = getUserDAOBean.exec(requestQnACommentSaveDTO.getUserId());

        // 댓글 작성한 유저 send comment count 증가
        writeUserDAO = updateUserSendCommentCountDAOBean.exec(requestQnACommentSaveDTO, writeUserDAO);

        // 경험치 추가
        writeUserDAO = updateUserExpDAOBean.exec(requestQnACommentSaveDTO, writeUserDAO);

        // QnA 게시물 메타데이터 변동
        QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(requestQnACommentSaveDTO.getQnaPostId());
        if (qnAPostMeta == null) return 0L;
        updateQnAPostMetaDAOBean.exec(qnaCommentDAO, qnaPostDAO, qnAPostMeta);

        // 댓글 저장
        saveCommentDAOBean.exec(qnaCommentDAO);

        // 게시물 저장
        savePostDAOBean.exec(qnaPostDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(writeUserDAO);

        // QnA 게시물 메타데이터 저장
        saveQnAPostMetaDAOBean.exec(qnAPostMeta);

        // 댓글 cId 반환
        return qnaCommentId;
    }
}
