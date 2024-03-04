package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.Model.DTO.qna.*;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import com.shownf.reptile.config.UserExpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserExpDAOBean {

    private final UserExpConfig userExpConfig;

    @Autowired
    public UpdateUserExpDAOBean(UserExpConfig userExpConfig) {
        this.userExpConfig = userExpConfig;
    }

    // User exp update based on post save
    public UserDAO exec(RequestPostSaveDTO requestPostSaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getPost());
        return userDAO;
    }

    // User exp update based on qna post save
    public UserDAO exec(RequestQnAPostSaveDTO requestQnAPostSaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getPost());
        return userDAO;
    }

    // User exp update based on post delete
    public UserDAO exec(RequestPostDeleteDTO requestPostDeleteDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getPost());
        return userDAO;
    }

    // User exp update based on qna post delete
    public UserDAO exec(RequestQnAPostDeleteDTO requestQnAPostSaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getPost());
        return userDAO;
    }

    // User exp update based on comment save
    public UserDAO exec(RequestCommentSaveDTO requestCommentSaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getComment());
        return userDAO;
    }

    // User exp update based on comment delete
    public UserDAO exec(RequestCommentDeleteDTO requestCommentDeleteDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getComment());
        return userDAO;
    }

    // User exp update based on QnA comment save
    public UserDAO exec(RequestQnACommentSaveDTO requestQnACommentSaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getComment());
        return userDAO;
    }

    // User exp update based on QnA comment delete
    public void exec(RequestQnACommentDeleteDTO requestQnACommentDeleteDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getComment());
    }

    // User exp update based on reply save
    public UserDAO exec(RequestReplySaveDTO requestReplySaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getReply());
        return userDAO;
    }

    // User exp update based on reply delete
    public UserDAO exec(RequestReplyDeleteDTO requestReplyDeleteDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getReply());
        return userDAO;
    }

    // User exp update based on reply save
    public void exec(RequestQnAReplySaveDTO requestQnAReplySaveDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getReply());
    }

    // User exp update based on reply delete
    public UserDAO exec(RequestQnAReplyDeleteDTO requestQnAReplyDeleteDTO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getReply());
        return userDAO;
    }

    // User exp update based on postHeart save
    public UserDAO exec(PostHeartDAO postHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on postHeart delete
    public UserDAO exec(String check, PostHeartDAO postHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on qna postHeart save
    public UserDAO exec(QnAPostHeartDAO qnaPostHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on qna postHeart delete
    public UserDAO exec(String check, QnAPostHeartDAO qnAPostHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on commentHeart save
    public UserDAO exec(CommentHeartDAO commentHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on commentHeart delete
    public UserDAO exec(String check, CommentHeartDAO commentHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on qna commentHeart save
    public UserDAO exec(QnACommentHeartDAO qnACommentHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on qna commentHeart delete
    public UserDAO exec(String check, QnACommentHeartDAO qnACommentHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on replyHeart save
    public UserDAO exec(ReplyHeartDAO replyHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on replyHeart delete
    public UserDAO exec(String check, ReplyHeartDAO replyHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on qna replyHeart save
    public UserDAO exec(QnAReplyHeartDAO qnAReplyHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getHeart());
        return userDAO;
    }

    // User exp update based on qna replyHeart delete
    public void exec(String check, QnAReplyHeartDAO qnAReplyHeartDAO, UserDAO userDAO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getHeart());
    }

    // 댓글 채택된 유저 경험치 증가
    public void exec(UserDAO userDAO, RequestQnACommentSelectionSaveDTO requestQnACommentSelectionSaveDTO){
        userDAO.setExp(userDAO.getExp() + userExpConfig.getSelection());
    }

    // 댓글 채택 삭제된 유저 경험치 감소
    public void exec(UserDAO userDAO, RequestQnACommentSelectionDeleteDTO requestQnACommentSelectionDeleteDTO){
        userDAO.setExp(userDAO.getExp() - userExpConfig.getSelection());
    }
}
