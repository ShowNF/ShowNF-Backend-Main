package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
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
}
