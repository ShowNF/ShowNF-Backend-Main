package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveCommentHeartBean {

    CreateUniqueIdBean createUniqueIdBean;
    CreateCommentHeartDAOBean createCommentHeartDAOBean;
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;
    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean;

    @Autowired
    public SaveCommentHeartBean(CreateUniqueIdBean createUniqueIdBean, CreateCommentHeartDAOBean createCommentHeartDAOBean, SaveCommentHeartDAOBean saveCommentHeartDAOBean, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, SaveCommentDAOBean saveCommentDAOBean, UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createCommentHeartDAOBean = createCommentHeartDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.updateUserHeartCountDAOBean = updateUserHeartCountDAOBean;
    }

    // 댓글 좋아요 저장
    public Long exec(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {

        // commentHeartId 생성
        Long commentHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        CommentHeartDAO commentHeartDAO = createCommentHeartDAOBean.exec(commentHeartId, requestCommentHeartSaveDTO);

        // 댓글 좋아요 저장
        saveCommentHeartDAOBean.exec(commentHeartDAO);

        // 댓글 좋아요 갯수 추가
        CommentDAO commentDAO = updateCommentHeartCountDAOBean.exec(commentHeartDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 유저 좋아요 갯수 추가
        updateUserHeartCountDAOBean.exec(requestCommentHeartSaveDTO);

        // commentHeartId 반환
        return commentHeartId;
    }
}
