package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentUpdateDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateCommentBean {

    GetCommentDAOBean getCommentDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateCommentDAOBean updateCommentDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;

    @Autowired
    public UpdateCommentBean(GetCommentDAOBean getCommentDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateCommentDAOBean updateCommentDAOBean, SaveCommentDAOBean saveCommentDAOBean) {
        this.getCommentDAOBean = getCommentDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateCommentDAOBean = updateCommentDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
    }

    // Update the comment
    public Long exec(RequestCommentUpdateDTO requestCommentUpdateDTO, HttpServletRequest request){

        // 댓글 가져오기
        CommentDAO commentDAO = getCommentDAOBean.exec(requestCommentUpdateDTO.getCommentId());
        if (commentDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestCommentUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        // 유저 토큰 확인
        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // 댓글 수정
        CommentDAO updateCommentDAO = updateCommentDAOBean.exec(commentDAO, requestCommentUpdateDTO);

        // 댓글 저장
        saveCommentDAOBean.exec(updateCommentDAO);

        return requestCommentUpdateDTO.getCommentId();
    }
}
