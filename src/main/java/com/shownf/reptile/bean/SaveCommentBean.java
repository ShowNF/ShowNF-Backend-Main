package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveCommentBean {

    CreateUniqueIdBean createUniqueIdBean;
    CreateCommentDAOBean createCommentDAOBean;
    UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveCommentBean(CreateUniqueIdBean createUniqueIdBean, CreateCommentDAOBean createCommentDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveCommentDAOBean saveCommentDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createCommentDAOBean = createCommentDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 댓글 저장
    public Long exec(RequestCommentSaveDTO requestCommentSaveDTO){

        // commentId 생성
        long commentId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        CommentDAO commentDAO = createCommentDAOBean.exec(commentId, requestCommentSaveDTO);

        // 댓글 저장에 따른 게시물 댓글 갯수 추가
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(commentDAO);
        if (postDAO == null) return 0L;

        // 댓글 추가시 유저 댓글 수 증가
        UserDAO userDAO = updateUserCommentCountDAOBean.exec(requestCommentSaveDTO);
        if (userDAO == null) return 0L;

        // 댓글 작성한 유저
        UserDAO userDAO1 = getUserDAOBean.exec(requestCommentSaveDTO.getUserId());

        // 경험치 추가
        userDAO1 = updateUserExpDAOBean.exec(requestCommentSaveDTO, userDAO1);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);

        // 댓글 cId 반환
        return commentId;
    }
}
