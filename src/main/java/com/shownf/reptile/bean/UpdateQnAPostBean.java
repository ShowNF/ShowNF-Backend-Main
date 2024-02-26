package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateQnAPostBean {

    GetQnAPostDAOBean getQnAPostDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateQnAPostDAOBean updateQnAPostDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;

    @Autowired
    public UpdateQnAPostBean(GetQnAPostDAOBean getQnAPostDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateQnAPostDAOBean updateQnAPostDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean) {
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateQnAPostDAOBean = updateQnAPostDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
    }

    // Update the post
    public Long exec(RequestQnAPostUpdateDTO requestQnAPostUpdateDTO, HttpServletRequest request){

        // QnA 게시물 찾기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(requestQnAPostUpdateDTO.getQnaPostId());
        if (qnAPostDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestQnAPostUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // 게시물 수정
        updateQnAPostDAOBean.exec(requestQnAPostUpdateDTO, qnAPostDAO);

        // 게시물 저장
        saveQnAPostDAOBean.exec(qnAPostDAO);

        return requestQnAPostUpdateDTO.getQnaPostId();
    }
}
