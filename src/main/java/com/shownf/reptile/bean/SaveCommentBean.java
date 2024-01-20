package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
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
    UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    SavePostMetaDAOBean savePostMetaDAOBean;

    @Autowired
    public SaveCommentBean(CreateUniqueIdBean createUniqueIdBean, CreateCommentDAOBean createCommentDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveCommentDAOBean saveCommentDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean, SavePostMetaDAOBean savePostMetaDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createCommentDAOBean = createCommentDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserSendCommentCountDAOBean = updateUserSendCommentCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.savePostMetaDAOBean = savePostMetaDAOBean;
    }

    // 댓글 저장
    public Long exec(RequestCommentSaveDTO requestCommentSaveDTO){

        // commentId 생성
        long commentId = createUniqueIdBean.exec();

        // 댓글 객체 생성
        CommentDAO commentDAO = createCommentDAOBean.exec(commentId, requestCommentSaveDTO);

        // 댓글 저장에 따른 게시물 댓글 갯수 추가
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(commentDAO);
        if (postDAO == null) return 0L;

        // 댓글 추가시 게시물 작성 유저 댓글 수 증가
        UserDAO userDAO = updateUserCommentCountDAOBean.exec(requestCommentSaveDTO);
        if (userDAO == null) return 0L;

        // 댓글 작성한 유저
        UserDAO userDAO1 = getUserDAOBean.exec(requestCommentSaveDTO.getUserId());

        // 댓글 작성한 유저 send comment count 증가
        userDAO1 = updateUserSendCommentCountDAOBean.exec(requestCommentSaveDTO, userDAO1);

        // 경험치 추가
        userDAO1 = updateUserExpDAOBean.exec(requestCommentSaveDTO, userDAO1);

        // 게시물 메타데이터 변동
        PostMeta postMeta = updatePostCommentCountDAOBean.exec(postDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);

        // 게시물 메타데이터 저장
        savePostMetaDAOBean.exec(postMeta);

        // 댓글 cId 반환
        return commentId;
    }
}
