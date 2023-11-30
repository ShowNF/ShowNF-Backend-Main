package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplyHeartDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteReplyHeartBean {

    GetReplyHeartDAOBean getReplyHeartDAOBean;
    CheckReplyIdReplyDAOBean checkReplyIdReplyDAOBean;
    CheckUserIdReplyDAOBean checkUserIdReplyDAOBean;
    UpdateReplyHeartCountDAOBean updateReplyHeartCountDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    DeleteReplyHeartDAOBean deleteReplyHeartDAOBean;
    SaveReplyDAOBean saveReplyDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteReplyHeartBean(GetReplyHeartDAOBean getReplyHeartDAOBean, CheckReplyIdReplyDAOBean checkReplyIdReplyDAOBean, CheckUserIdReplyDAOBean checkUserIdReplyDAOBean, UpdateReplyHeartCountDAOBean updateReplyHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, DeleteReplyHeartDAOBean deleteReplyHeartDAOBean, SaveReplyDAOBean saveReplyDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getReplyHeartDAOBean = getReplyHeartDAOBean;
        this.checkReplyIdReplyDAOBean = checkReplyIdReplyDAOBean;
        this.checkUserIdReplyDAOBean = checkUserIdReplyDAOBean;
        this.updateReplyHeartCountDAOBean = updateReplyHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.deleteReplyHeartDAOBean = deleteReplyHeartDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 대댓글 좋아요 삭제
    public Long exec(RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO){

        // 댓글과 유저 아이디로 삭제할 좋아요 찾기
        ReplyHeartDAO replyHeartDAO = getReplyHeartDAOBean.exec(requestReplyHeartDeleteDTO);

        // 취소 중복 배제
        if (replyHeartDAO == null)
            return 0L;

        // 대댓글 좋아요 해당하는 대댓글 확인
        if (!checkReplyIdReplyDAOBean.exec(replyHeartDAO, requestReplyHeartDeleteDTO))
            return null;

        // 대댓글 좋아요 해당하는 유저 확인
        if (!checkUserIdReplyDAOBean.exec(replyHeartDAO, requestReplyHeartDeleteDTO))
            return null;

        // 대댓글 좋아요 갯수 감소
        ReplyDAO replyDAO = updateReplyHeartCountDAOBean.exec(replyHeartDAO.getReplyHeartId(), replyHeartDAO);
        if (replyDAO == null) return null;

        // 좋아요 sender, receiver 삭제
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(null, replyDAO);
        if (userDAO1 == null) return 0L;

        UserDAO userDAO2 = updateUserSendHeartDAOBean.exec(null, replyHeartDAO);
        if (userDAO2 == null) return 0L;

        // 대댓글 삭제
        deleteReplyHeartDAOBean.exec(replyHeartDAO);

        // 대댓글 저장
        saveReplyDAOBean.exec(replyDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        /*// 대댓글 좋아요 삭제시 유저 좋아요수 감소
        updateUserHeartCountDAOBean.exec(requestReplyHeartDeleteDTO);*/

        // replyHeartId 반환
        return replyHeartDAO.getReplyHeartId();
    }
}
