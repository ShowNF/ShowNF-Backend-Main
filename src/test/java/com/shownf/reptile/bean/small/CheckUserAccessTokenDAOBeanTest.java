package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.*;
import com.shownf.reptile.repository.GoogleUserRepositoryJPA;
import com.shownf.reptile.repository.KakaoUserRepositoryJPA;
import com.shownf.reptile.repository.NaverUserRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckUserAccessTokenDAOBeanTest {

    @Mock
    private UserRepositoryJPA userRepositoryJPA;

    @Mock
    private KakaoUserRepositoryJPA kakaoUserRepositoryJPA;

    @Mock
    private GoogleUserRepositoryJPA googleUserRepositoryJPA;

    @Mock
    private NaverUserRepositoryJPA naverUserRepositoryJPA;

    @InjectMocks
    private CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;

    @Test
    public void testCheckTokenForString() {
        MockitoAnnotations.initMocks(this);

        // Mocking the request
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("access-token")).thenReturn("token");

        // Mocking the repositories
        when(kakaoUserRepositoryJPA.findByKakaoId(anyString())).thenReturn(new KakaoUserDAO("oauthId", "token", null));
        when(googleUserRepositoryJPA.findByGoogleId(anyString())).thenReturn(null);
        when(naverUserRepositoryJPA.findByNaverId(anyString())).thenReturn(null);

        // Test with KakaoUserDAO
        assertTrue(checkUserAccessTokenDAOBean.exec("oauthId", request));
    }

    @Test
    public void testCheckTokenForUserDAO() {
        MockitoAnnotations.initMocks(this);

        // Mocking the request
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("access-token")).thenReturn("token");

        UserDAO userDAO = new UserDAO();
        userDAO.setOauthId("oauthId");

        // Mocking the repositories
        when(kakaoUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(new KakaoUserDAO("oauthId", "token", null));
        when(googleUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);
        when(naverUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);

        // Test with KakaoUserDAO
        assertTrue(checkUserAccessTokenDAOBean.exec(userDAO, request));
    }

    @Test
    public void testCheckTokenForPostDAO() {
        MockitoAnnotations.initMocks(this);

        // Mocking the request
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("access-token")).thenReturn("token");

        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);
        userDAO.setOauthId("oauthId");

        when(userRepositoryJPA.findById(postDAO.getUserId())).thenReturn(Optional.of(userDAO));

        // Mocking the repositories
        when(kakaoUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(new KakaoUserDAO("oauthId", "token", null));
        when(googleUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);
        when(naverUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);

        // Test with KakaoUserDAO
        assertTrue(checkUserAccessTokenDAOBean.exec(postDAO, request));
    }

    @Test
    public void testCheckTokenForCommentDAO() {
        MockitoAnnotations.initMocks(this);

        // Mocking the request
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("access-token")).thenReturn("token");

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);
        userDAO.setOauthId("oauthId");

        when(userRepositoryJPA.findById(commentDAO.getUserId())).thenReturn(Optional.of(userDAO));

        // Mocking the repositories
        when(kakaoUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(new KakaoUserDAO("oauthId", "token", null));
        when(googleUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);
        when(naverUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);

        // Test with KakaoUserDAO
        assertTrue(checkUserAccessTokenDAOBean.exec(commentDAO, request));
    }

    @Test
    public void testCheckTokenForReplyDAO() {
        MockitoAnnotations.initMocks(this);

        // Mocking the request
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("access-token")).thenReturn("token");

        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);
        userDAO.setOauthId("oauthId");

        when(userRepositoryJPA.findById(replyDAO.getUserId())).thenReturn(Optional.of(userDAO));

        // Mocking the repositories
        when(kakaoUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(new KakaoUserDAO("oauthId", "token", null));
        when(googleUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);
        when(naverUserRepositoryJPA.findByAccessToken(anyString())).thenReturn(null);

        // Test with KakaoUserDAO
        assertTrue(checkUserAccessTokenDAOBean.exec(replyDAO, request));
    }
}
