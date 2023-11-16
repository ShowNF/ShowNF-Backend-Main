package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplyHeartSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveReplyHeartBean {

    GetReplyHeartDAOBean getReplyHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreateReplyHeartDAOBean createReplyHeartDAOBean;
    UpdateReplyHeartCountDAOBean updateReplyHeartCountDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    SaveReplyHeartDAOBean saveReplyHeartDAOBean;
    SaveReplyDAOBean saveReplyDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveReplyHeartBean(GetReplyHeartDAOBean getReplyHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateReplyHeartDAOBean createReplyHeartDAOBean, UpdateReplyHeartCountDAOBean updateReplyHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, SaveReplyHeartDAOBean saveReplyHeartDAOBean, SaveReplyDAOBean saveReplyDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getReplyHeartDAOBean = getReplyHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createReplyHeartDAOBean = createReplyHeartDAOBean;
        this.updateReplyHeartCountDAOBean = updateReplyHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.saveReplyHeartDAOBean = saveReplyHeartDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 대댓글 좋아요 저장
    public Long exec(RequestReplyHeartSaveDTO requestReplyHeartSaveDTO){

        // 대댓글 좋아요 중복 배제
        if (getReplyHeartDAOBean.exec(requestReplyHeartSaveDTO) != null)
            return 0L;

        // replyHeartId 생성
        Long replyHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        ReplyHeartDAO replyHeartDAO = createReplyHeartDAOBean.exec(replyHeartId, requestReplyHeartSaveDTO);

        // 대댓글 좋아요 갯수 추가
        ReplyDAO replyDAO = updateReplyHeartCountDAOBean.exec(replyHeartDAO);
        if (replyDAO == null) return null;

        // 좋아요 sender, receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(replyDAO);
        if (userDAO1 == null) return 0L;

        UserDAO userDAO2 = updateUserSendHeartDAOBean.exec(replyHeartDAO);
        if (userDAO2 == null) return 0L;

        // 대댓글 좋아요 저장
        saveReplyHeartDAOBean.exec(replyHeartDAO);

        // 대댓글 저장
        saveReplyDAOBean.exec(replyDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        /*// 유저 좋아요 갯수 추가
        updateUserHeartCountDAOBean.exec(requestReplyHeartSaveDTO);*/

        // replyHeartId 반환
        return replyHeartId;
    }
}
