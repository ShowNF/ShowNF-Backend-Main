package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveReplyBean {

    CreateUniqueIdBean createUniqueIdBean;
    CreateReplyDAOBean createReplyDAOBean;
    UpdateCommentReplyCountDAOBean updateCommentReplyCountDAOBean;
    UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveReplyDAOBean saveReplyDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveReplyBean(CreateUniqueIdBean createUniqueIdBean, CreateReplyDAOBean createReplyDAOBean, UpdateCommentReplyCountDAOBean updateCommentReplyCountDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveReplyDAOBean saveReplyDAOBean, SaveCommentDAOBean saveCommentDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createReplyDAOBean = createReplyDAOBean;
        this.updateCommentReplyCountDAOBean = updateCommentReplyCountDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 대댓글 저장
    public Long exec(RequestReplySaveDTO requestReplySaveDTO){

        // replyId 생성
        Long replyId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        ReplyDAO replyDAO = createReplyDAOBean.exec(replyId, requestReplySaveDTO);

        // 대댓글 저장에 따른 댓글 대댓글 갯수 추가
        CommentDAO commentDAO = updateCommentReplyCountDAOBean.exec(replyDAO);
        if (commentDAO == null)
            return 0L;

        // 대댓글 저장에 따른 게시물 댓글 갯수 추가
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(commentDAO);
        if (postDAO == null)
            return 0L;

        // 대댓글 저장시 유저 댓글수 증가
        UserDAO userDAO = updateUserCommentCountDAOBean.exec(requestReplySaveDTO);
        if (userDAO == null)
            return 0L;

        // 대댓글 작성한 유저
        UserDAO userDAO1 = getUserDAOBean.exec(requestReplySaveDTO.getUserId());

        // 경험치 추가
        userDAO1 = updateUserExpDAOBean.exec(requestReplySaveDTO, userDAO1);

        // 대댓글 저장
        saveReplyDAOBean.exec(replyDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);

        // 대댓글 replyId 반환
        return replyId;
    }
}
