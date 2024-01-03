package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.*;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import com.shownf.reptile.repository.NaverUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class CheckUserAccessTokenDAOBean {

    UserRepositoryJPA userRepositoryJPA;
    KakaoUserRepositoryJPA kakaoUserRepositoryJPA;
    GoogleUserRepositoryJPA googleUserRepositoryJPA;
    NaverUserRepositoryJPA naverUserRepositoryJPA;

    @Autowired
    public CheckUserAccessTokenDAOBean(UserRepositoryJPA userRepositoryJPA, KakaoUserRepositoryJPA kakaoUserRepositoryJPA, GoogleUserRepositoryJPA googleUserRepositoryJPA, NaverUserRepositoryJPA naverUserRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.kakaoUserRepositoryJPA = kakaoUserRepositoryJPA;
        this.googleUserRepositoryJPA = googleUserRepositoryJPA;
        this.naverUserRepositoryJPA = naverUserRepositoryJPA;
    }

    // 토큰 일치 확인
    public boolean exec(String oauthId, HttpServletRequest request){

        String userToken = ((HttpServletRequest) request).getHeader("access-token");

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByKakaoId(oauthId);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByGoogleId(oauthId);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByNaverId(oauthId);

        String savedToken;

        if (kakaoUserDAO != null) savedToken = kakaoUserDAO.getAccessToken();
        else if (googleUserDAO != null) savedToken = googleUserDAO.getAccessToken();
        else if (naverUserDAO != null) savedToken = naverUserDAO.getAccessToken();
        else savedToken = null;

        return userToken.equals(savedToken);
    }

    // 아이디 일치 확인
    public boolean exec(UserDAO userDAO, HttpServletRequest request){

        String userToken = ((HttpServletRequest) request).getHeader("access-token");

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(userToken);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(userToken);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByAccessToken(userToken);

        String savedUserId;

        if (kakaoUserDAO != null) savedUserId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null) savedUserId = googleUserDAO.getGoogleId();
        else if (naverUserDAO != null) savedUserId = naverUserDAO.getNaverId();
        else savedUserId = null;

        return userDAO.getOauthId().equals(savedUserId);
    }

    // 게시물 삭제시 토큰 확인
    public boolean exec(PostDAO postDAO, HttpServletRequest request){

        UserDAO userDAO = userRepositoryJPA.findById(postDAO.getUserId()).orElse(null);
        if (userDAO == null) return false;

        String userToken = ((HttpServletRequest) request).getHeader("access-token");

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(userToken);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(userToken);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByAccessToken(userToken);

        String savedUserId;

        if (kakaoUserDAO != null) savedUserId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null) savedUserId = googleUserDAO.getGoogleId();
        else if (naverUserDAO != null) savedUserId = naverUserDAO.getNaverId();
        else savedUserId = null;

        return userDAO.getOauthId().equals(savedUserId);
    }

    // 댓글 삭제시 토큰 확인
    public boolean exec(CommentDAO commentDAO, HttpServletRequest request){

        UserDAO userDAO = userRepositoryJPA.findById(commentDAO.getUserId()).orElse(null);
        if (userDAO == null) return false;

        String userToken = ((HttpServletRequest) request).getHeader("access-token");

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(userToken);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(userToken);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByAccessToken(userToken);

        String savedUserId;

        if (kakaoUserDAO != null) savedUserId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null) savedUserId = googleUserDAO.getGoogleId();
        else if (naverUserDAO != null) savedUserId = naverUserDAO.getNaverId();
        else savedUserId = null;

        return userDAO.getOauthId().equals(savedUserId);
    }

    // 대댓글 삭제시 토큰 확인
    public boolean exec(ReplyDAO replyDAO, HttpServletRequest request){

        UserDAO userDAO = userRepositoryJPA.findById(replyDAO.getUserId()).orElse(null);
        if (userDAO == null) return false;

        String userToken = ((HttpServletRequest) request).getHeader("access-token");

        KakaoUserDAO kakaoUserDAO = kakaoUserRepositoryJPA.findByAccessToken(userToken);
        GoogleUserDAO googleUserDAO = googleUserRepositoryJPA.findByAccessToken(userToken);
        NaverUserDAO naverUserDAO = naverUserRepositoryJPA.findByAccessToken(userToken);

        String savedUserId;

        if (kakaoUserDAO != null) savedUserId = kakaoUserDAO.getKakaoId();
        else if (googleUserDAO != null) savedUserId = googleUserDAO.getGoogleId();
        else if (naverUserDAO != null) savedUserId = naverUserDAO.getNaverId();
        else savedUserId = null;

        return userDAO.getOauthId().equals(savedUserId);
    }
}
