package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSendCommentCountDAOBean {

    // Increase the user's 'send comment count' when writing a comment.
    public UserDAO exec(RequestCommentSaveDTO requestCommentSaveDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() + 1);
        return userDAO;
    }

    // Decrease the user's 'send comment count' when delete a comment.
    public UserDAO exec(RequestCommentDeleteDTO requestCommentDeleteDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() - 1);
        return userDAO;
    }

    // Increase the user's 'send comment count' when writing a reply.
    public UserDAO exec(RequestReplySaveDTO requestReplySaveDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() + 1);
        return userDAO;
    }

    // Decrease the user's 'send comment count' when delete a reply.
    public UserDAO exec(RequestReplyDeleteDTO requestReplyDeleteDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() - 1);
        return userDAO;
    }

    // Increase the user's 'send qna comment count' when writing a comment.
    public UserDAO exec(RequestQnACommentSaveDTO requestQnACommentSaveDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() + 1);
        return userDAO;
    }

    // Decrease the user's 'send QnA comment count' when delete a comment.
    public void exec(RequestQnACommentDeleteDTO requestQnACommentDeleteDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() - 1);
    }

    // Increase the user's 'send qna Reply count' when writing a comment.
    public void exec(RequestQnAReplySaveDTO requestQnAReplySaveDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() + 1);
    }

    // Decrease the user's 'send QnA comment count' when delete a comment.
}
