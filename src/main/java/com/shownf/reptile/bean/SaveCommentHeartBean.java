package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
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
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;

    @Autowired
    public SaveCommentHeartBean(GetCommentHeartDAOBean getCommentHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateCommentHeartDAOBean createCommentHeartDAOBean, SaveCommentHeartDAOBean saveCommentHeartDAOBean, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, SaveCommentDAOBean saveCommentDAOBean) {
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createCommentHeartDAOBean = createCommentHeartDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
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

        // 댓글 좋아요 저장
        saveCommentHeartDAOBean.exec(commentHeartDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        /*// 유저 좋아요 갯수 추가
        updateUserHeartCountDAOBean.exec(requestCommentHeartSaveDTO);*/

        // commentHeartId 반환
        return commentHeartId;
    }
}
