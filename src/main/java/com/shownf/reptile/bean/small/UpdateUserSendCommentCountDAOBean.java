package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSendCommentCountDAOBean {

    // Increase the user's 'send comment count' when writing a comment.
    public UserDAO exec(RequestCommentSaveDTO requestCommentSaveDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() + 1);
        return userDAO;
    }

    // Increase the user's 'send comment count' when writing a comment.
    public UserDAO exec(RequestCommentDeleteDTO requestCommentDeleteDTO, UserDAO userDAO){
        userDAO.setSendCommentCount(userDAO.getSendCommentCount() - 1);
        return userDAO;
    }
}
