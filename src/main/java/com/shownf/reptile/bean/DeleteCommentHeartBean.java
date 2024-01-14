package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCommentHeartBean {

    GetCommentHeartDAOBean getCommentHeartDAOBean;
    CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean;
    CheckUserIdCommentDAOBean checkUserIdCommentDAOBean;
    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    DeleteCommentHeartDAOBean deleteCommentHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteCommentHeartBean(GetCommentHeartDAOBean getCommentHeartDAOBean, CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean, CheckUserIdCommentDAOBean checkUserIdCommentDAOBean, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, DeleteCommentHeartDAOBean deleteCommentHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveCommentDAOBean saveCommentDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
        this.checkCommentIdCommentDAOBean = checkCommentIdCommentDAOBean;
        this.checkUserIdCommentDAOBean = checkUserIdCommentDAOBean;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.deleteCommentHeartDAOBean = deleteCommentHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public Long exec(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        // 아이디로 삭제할 좋아요 찾기
        CommentHeartDAO commentHeartDAO = getCommentHeartDAOBean.exec(requestCommentHeartDeleteDTO);

        // 취소 중복 배제
        if (commentHeartDAO == null)
            return 0L;

        // 댓글 좋아요 해당하는 댓글 확인
        if (!checkCommentIdCommentDAOBean.exec(commentHeartDAO, requestCommentHeartDeleteDTO))
            return null;

        // 댓글 좋아요 해당하는 유저 확인
        if (!checkUserIdCommentDAOBean.exec(commentHeartDAO, requestCommentHeartDeleteDTO))
            return null;

        // 댓글 좋아요 갯수 감소
        CommentDAO commentDAO = updateCommentHeartCountDAOBean.exec(commentHeartDAO.getCommentHeartId(), commentHeartDAO);
        if (commentDAO == null) return null;

        // 좋아요 sender, receiver 감소
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(null, commentDAO);
        if (userDAO1 == null) return 0L;

        UserDAO userDAO2;
        if (requestCommentHeartDeleteDTO.getUserId().equals(userDAO1.getUserId()))
            userDAO2 = updateUserSendHeartDAOBean.exec(null, commentHeartDAO, userDAO1);
        else userDAO2 = updateUserSendHeartDAOBean.exec(null, commentHeartDAO);
        if (userDAO2 == null) return 0L;

        // 경험치 삭제
        userDAO2 = updateUserExpDAOBean.exec(null, commentHeartDAO, userDAO2);

        // 좋아요 삭제
        deleteCommentHeartDAOBean.exec(commentHeartDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // commentHeartId 반환
        return commentHeartDAO.getCommentHeartId();
    }
}
