package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.*;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserReceiveHeartDAOBean {

    GetUserDAOBean getUserDAOBean;

    @Autowired
    public UpdateUserReceiveHeartDAOBean(GetUserDAOBean getUserDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
    }

    // 게시물 좋아요시 좋아요 받은 유저 수정
    public UserDAO exec(PostDAO postDAO){

        // 좋아요 받은 유저 아이디
        Long userId = postDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 추가
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() + 1);

        return userDAO;
    }

    // 게시물 좋아요 삭제시 좋아요 받은 유저 수정
    public UserDAO exec(Long check, PostDAO postDAO){

        // 좋아요 받은 유저 아이디
        Long userId = postDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 감소
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() - 1);

        return userDAO;
    }

    // QnA 게시물 좋아요시 좋아요 받은 유저 수정
    public UserDAO exec(QnAPostDAO qnaPostDAO){

        // 좋아요 받은 유저 아이디
        Long userId = qnaPostDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 추가
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() + 1);

        return userDAO;
    }

    // QnA 게시물 좋아요 삭제시 좋아요 받은 유저 수정
    public UserDAO exec(Long check, QnAPostDAO qnaPostDAO){

        // 좋아요 받은 유저 아이디
        Long userId = qnaPostDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 추가
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() - 1);

        return userDAO;
    }

    // 댓글 좋아요시 좋아요 받은 유저 수정
    public UserDAO exec(CommentDAO commentDAO){

        // 좋아요 받은 유저 아이디
        Long userId = commentDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 추가
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() + 1);

        return userDAO;
    }

    // 댓글 좋아요 삭제시 좋아요 받은 유저 수정
    public UserDAO exec(Long check, CommentDAO commentDAO){

        // 좋아요 받은 유저 아이디
        Long userId = commentDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 감소
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() - 1);

        return userDAO;
    }

    // 대댓글 좋아요시 좋아요 받은 유저 수정
    public UserDAO exec(ReplyDAO replyDAO){

        // 좋아요 받은 유저 아이디
        Long userId = replyDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 추가
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() + 1);

        return userDAO;
    }

    // 대댓글 좋아요 삭제시 좋아요 받은 유저 수정
    public UserDAO exec(Long check, ReplyDAO replyDAO){

        // 좋아요 받은 유저 아이디
        Long userId = replyDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 receiveHeart 감소
        userDAO.setReceiveHeartCount(userDAO.getReceiveHeartCount() - 1);

        return userDAO;
    }
}
