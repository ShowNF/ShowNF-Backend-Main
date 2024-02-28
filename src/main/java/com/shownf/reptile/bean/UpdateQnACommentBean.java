package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateQnACommentBean {

    GetQnACommentDAOBean getQnACommentDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateQnACommentDAOBean updateQnACommentDAOBean;
    SaveQnACommentDAOBean saveQnACommentDAOBean;

    @Autowired
    public UpdateQnACommentBean(GetQnACommentDAOBean getQnACommentDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateQnACommentDAOBean updateQnACommentDAOBean, SaveQnACommentDAOBean saveQnACommentDAOBean) {
        this.getQnACommentDAOBean = getQnACommentDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateQnACommentDAOBean = updateQnACommentDAOBean;
        this.saveQnACommentDAOBean = saveQnACommentDAOBean;
    }


    // QnA 댓글 수정
    public Long exec(RequestQnACommentUpdateDTO requestQnACommentUpdateDTO, HttpServletRequest request){

        // QnA 댓글 가져오기
        QnACommentDAO qnaCommentDAO = getQnACommentDAOBean.exec(requestQnACommentUpdateDTO.getQnaCommentId());
        if (qnaCommentDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestQnACommentUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        // 유저 토큰 확인
        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // 댓글 수정
        updateQnACommentDAOBean.exec(qnaCommentDAO, requestQnACommentUpdateDTO);

        // 댓글 저장
        saveQnACommentDAOBean.exec(qnaCommentDAO);

        return requestQnACommentUpdateDTO.getQnaCommentId();
    }
}
