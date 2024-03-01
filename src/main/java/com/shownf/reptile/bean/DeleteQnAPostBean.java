package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostDeleteDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteQnAPostBean {

    GetQnAPostDAOBean getQnAPostDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateUserDAOBean updateUserDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteQnAPostBean(GetQnAPostDAOBean getQnAPostDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateUserDAOBean updateUserDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateUserDAOBean = updateUserDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveQnAPostMetaDAOBean = saveQnAPostMetaDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }


    // Delete the post
    public Long exec(RequestQnAPostDeleteDTO requestQnAPostDeleteDTO, HttpServletRequest request){

        // 삭제할 QnA 게시글 찾기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(requestQnAPostDeleteDTO.getQnaPostId());
        if (qnAPostDAO == null) return 0L;

        // 유저 QnA 게시물 토큰으로 확인
        UserDAO userDAO = getUserDAOBean.exec(requestQnAPostDeleteDTO.getUserId());
        if (userDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return null;

        // 게시물 deleteCheck 값 true 변경
        qnAPostDAO.setDeleteCheck(true);

        // 게시물 삭제시 유저 필드 변경
        updateUserDAOBean.exec(userDAO, qnAPostDAO);

        // 유저 경험치 변경
        updateUserExpDAOBean.exec(requestQnAPostDeleteDTO, userDAO);

        // QnA 게시물 메타데이터 찾기
        QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(requestQnAPostDeleteDTO.getQnaPostId());
        if (qnAPostMeta == null) return 0L;

        // QnA 게시물 메타데이터 deleteCheck 값 true 변경
        qnAPostMeta.setDeleteCheck(true);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnAPostDAO);

        // 게시물 메타데이터 저장
        saveQnAPostMetaDAOBean.exec(qnAPostMeta);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // qnaPostId 반환
        return requestQnAPostDeleteDTO.getQnaPostId();
    }
}
