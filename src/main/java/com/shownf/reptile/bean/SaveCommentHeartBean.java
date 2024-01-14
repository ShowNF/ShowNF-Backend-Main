package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveCommentHeartBean {

    GetCommentHeartDAOBean getCommentHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreateCommentHeartDAOBean createCommentHeartDAOBean;
    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveCommentHeartBean(GetCommentHeartDAOBean getCommentHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateCommentHeartDAOBean createCommentHeartDAOBean, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveCommentHeartDAOBean saveCommentHeartDAOBean, SaveCommentDAOBean saveCommentDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createCommentHeartDAOBean = createCommentHeartDAOBean;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 댓글 좋아요 저장
    public Long exec(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {

        // 댓글 좋아요 중복 배제
        if (getCommentHeartDAOBean.exec(requestCommentHeartSaveDTO) != null)
            return 0L;

        // commentHeartId 생성
        Long commentHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        CommentHeartDAO commentHeartDAO = createCommentHeartDAOBean.exec(commentHeartId, requestCommentHeartSaveDTO);

        // 댓글 좋아요 갯수 추가
        CommentDAO commentDAO = updateCommentHeartCountDAOBean.exec(commentHeartDAO);
        if (commentDAO == null) return 0L;

        // 좋아요 receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(commentDAO);
        if (userDAO1 == null) return 0L;

        // 좋아요 sender 추가
        UserDAO userDAO2;
        if (requestCommentHeartSaveDTO.getUserId().equals(userDAO1.getUserId()))
            userDAO2 = updateUserSendHeartDAOBean.exec(commentHeartDAO, userDAO1);
        else userDAO2 = updateUserSendHeartDAOBean.exec(commentHeartDAO);
        if (userDAO2 == null) return 0L;

        // 유저 경험치 추가
        userDAO2 = updateUserExpDAOBean.exec(commentHeartDAO, userDAO2);

        // 댓글 좋아요 저장
        saveCommentHeartDAOBean.exec(commentHeartDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // commentHeartId 반환
        return commentHeartId;
    }
}
