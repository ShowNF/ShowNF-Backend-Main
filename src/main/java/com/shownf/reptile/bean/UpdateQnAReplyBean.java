package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateQnAReplyBean {

    GetQnAReplyDAOBean getReplyDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateQnAReplyDAOBean updateQnAReplyDAOBean;
    SaveQnAReplyDAOBean saveQnAReplyDAOBean;

    @Autowired
    public UpdateQnAReplyBean(GetQnAReplyDAOBean getReplyDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateQnAReplyDAOBean updateQnAReplyDAOBean, SaveQnAReplyDAOBean saveQnAReplyDAOBean) {
        this.getReplyDAOBean = getReplyDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateQnAReplyDAOBean = updateQnAReplyDAOBean;
        this.saveQnAReplyDAOBean = saveQnAReplyDAOBean;
    }

    // Update the reply
    public Long exec(RequestQnAReplyUpdateDTO requestQnAReplyUpdateDTO, HttpServletRequest request){

        // 대댓글 가져오기
        QnAReplyDAO qnAReplyDAO = getReplyDAOBean.exec(requestQnAReplyUpdateDTO.getQnaReplyId());
        if (qnAReplyDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestQnAReplyUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        // 유저 토큰 확인
        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // 대댓글 수정
        updateQnAReplyDAOBean.exec(qnAReplyDAO, requestQnAReplyUpdateDTO);

        // 대댓글 저장
        saveQnAReplyDAOBean.exec(qnAReplyDAO);

        return requestQnAReplyUpdateDTO.getQnaReplyId();
    }
}
