package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserDAOBean {

    // 게시물 삭제시 유저 게시물 정보 감소
    public void exec(UserDAO userDAO, QnAPostDAO qnaPostDAO){

        // 유저 postCount 감소
        userDAO.setPostCount(userDAO.getPostCount() - 1);

        // 유저 receiveHeartCount 감소
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() - qnaPostDAO.getHeartCount());

        // 유저 commentCount 감소
        userDAO.setCommentCount(userDAO.getCommentCount() - qnaPostDAO.getCommentCount());

        return;
    }
}
