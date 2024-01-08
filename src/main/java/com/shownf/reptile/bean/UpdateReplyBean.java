package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplyUpdateDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateReplyBean {

    GetReplyDAOBean getReplyDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateReplyDAOBean updateReplyDAOBean;
    SaveReplyDAOBean saveReplyDAOBean;

    @Autowired
    public UpdateReplyBean(GetReplyDAOBean getReplyDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateReplyDAOBean updateReplyDAOBean, SaveReplyDAOBean saveReplyDAOBean) {
        this.getReplyDAOBean = getReplyDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateReplyDAOBean = updateReplyDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
    }

    // Update the reply
    public Long exec(RequestReplyUpdateDTO requestReplyUpdateDTO, HttpServletRequest request){

        // 대댓글 가져오기
        ReplyDAO replyDAO = getReplyDAOBean.exec(requestReplyUpdateDTO.getReplyId());
        if (replyDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestReplyUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        // 유저 토큰 확인
        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // 대댓글 수정
        ReplyDAO updateReplyDAO = updateReplyDAOBean.exec(replyDAO, requestReplyUpdateDTO);

        // 대댓글 저장
        saveReplyDAOBean.exec(updateReplyDAO);

        return requestReplyUpdateDTO.getReplyId();
    }
}
