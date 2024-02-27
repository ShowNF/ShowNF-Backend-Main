package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSendHeartDAOBean {

    GetUserDAOBean getUserDAOBean;

    @Autowired
    public UpdateUserSendHeartDAOBean(GetUserDAOBean getUserDAOBean) {
        this.getUserDAOBean = getUserDAOBean;
    }

    // 게시물 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(PostHeartDAO postHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = postHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 자신의 게시물 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(PostHeartDAO postHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 게시물 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, PostHeartDAO postHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = postHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }

    // 자신의 게시물 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, PostHeartDAO postHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }

    // QnA 게시물 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(QnAPostHeartDAO qnaPostHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = qnaPostHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 자신의 게시물 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(QnAPostHeartDAO qnaPostHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 댓글 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(CommentHeartDAO commentHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = commentHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 자신 댓글 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(CommentHeartDAO commentHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 댓글 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, CommentHeartDAO commentHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = commentHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }

    // 자신 댓글 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, CommentHeartDAO commentHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }

    // 대댓글 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(ReplyHeartDAO replyHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = replyHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 자신 대댓글 좋아요시 좋아요 보낸 유저 수정
    public UserDAO exec(ReplyHeartDAO replyHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 추가
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() + 1);

        return userDAO;
    }

    // 대댓글 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, ReplyHeartDAO replyHeartDAO){

        // 좋아요 보낸 유저 아이디
        Long userId = replyHeartDAO.getUserId();

        // 유저 객체 찾기
        UserDAO userDAO = getUserDAOBean.exec(userId);
        if (userDAO == null) return null;

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }

    // 자신 대댓글 좋아요 삭제시 좋아요 보낸 유저 수정
    public UserDAO exec(Long check, ReplyHeartDAO replyHeartDAO, UserDAO userDAO){

        // 유저 sendHeart 감소
        userDAO.setSendHeartCount(userDAO.getSendHeartCount() - 1);

        return userDAO;
    }
}
