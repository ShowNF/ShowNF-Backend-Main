package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
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
    UpdateCommentDAOBean updateCommentDAOBean;
    UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;
    UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveReplyDAOBean saveReplyDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    SavePostMetaDAOBean savePostMetaDAOBean;

    @Autowired
    public SaveReplyBean(CreateUniqueIdBean createUniqueIdBean, CreateReplyDAOBean createReplyDAOBean, UpdateCommentDAOBean updateCommentDAOBean, UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean, UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveReplyDAOBean saveReplyDAOBean, SaveCommentDAOBean saveCommentDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean, SavePostMetaDAOBean savePostMetaDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createReplyDAOBean = createReplyDAOBean;
        this.updateCommentDAOBean = updateCommentDAOBean;
        this.updatePostCommentCountDAOBean = updatePostCommentCountDAOBean;
        this.updateUserCommentCountDAOBean = updateUserCommentCountDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserSendCommentCountDAOBean = updateUserSendCommentCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveReplyDAOBean = saveReplyDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.savePostMetaDAOBean = savePostMetaDAOBean;
    }

    // 대댓글 저장
    public Long exec(RequestReplySaveDTO requestReplySaveDTO){

        // replyId 생성
        Long replyId = createUniqueIdBean.exec();

        // 대댓글 객체 생성
        ReplyDAO replyDAO = createReplyDAOBean.exec(replyId, requestReplySaveDTO);

        // 대댓글 저장에 따른 댓글 대댓글 갯수 추가
        CommentDAO commentDAO = updateCommentDAOBean.exec(replyDAO);
        if (commentDAO == null)
            return 0L;

        // 대댓글 저장에 따른 게시물 댓글 갯수 추가
        PostDAO postDAO = updatePostCommentCountDAOBean.exec(commentDAO);
        if (postDAO == null)
            return 0L;

        // 대댓글 저장시 댓글 작성한 유저 댓글수 증가
        UserDAO userDAO = updateUserCommentCountDAOBean.exec(requestReplySaveDTO);
        if (userDAO == null)
            return 0L;

        // 대댓글 작성한 유저
        UserDAO userDAO1 = getUserDAOBean.exec(requestReplySaveDTO.getUserId());

        // 대댓글 작성한 유저 send comment count 증가
        userDAO1 = updateUserSendCommentCountDAOBean.exec(requestReplySaveDTO, userDAO1);

        // 경험치 추가
        userDAO1 = updateUserExpDAOBean.exec(requestReplySaveDTO, userDAO1);

        // 게시물 메타데이터 변동
        PostMeta postMeta = updatePostCommentCountDAOBean.exec(postDAO);

        // 대댓글 저장
        saveReplyDAOBean.exec(replyDAO);

        // 댓글 저장
        saveCommentDAOBean.exec(commentDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);

        // 게시물 메타데이터 저장
        savePostMetaDAOBean.exec(postMeta);

        // 대댓글 replyId 반환
        return replyId;
    }
}
