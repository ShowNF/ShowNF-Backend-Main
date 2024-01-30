package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UpdateUserSendHeartDAOBeanTest {

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @InjectMocks
    private UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;

    @Test
    void testExecPostHeart() {
        // 가짜 데이터 설정
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(5);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(postHeartDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecPostHeartWithUserDAO() {
        // 가짜 데이터 설정
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(5);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(postHeartDAO, userDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecPostHeartDelete() {
        // 가짜 데이터 설정
        Long check = 1L;
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(8);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(check, postHeartDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(7);
    }

    @Test
    void testExecPostHeartDeleteWithUserDAO() {
        Long check = 1L;
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(8);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(check, postHeartDAO, userDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(7);
    }

    @Test
    void testExecCommentHeart() {
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(5);

        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(commentHeartDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecCommentHeartWithUserDAO() {
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(5);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(commentHeartDAO, userDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecCommentHeartDelete() {
        Long check = 1L;
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(8);

        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(check, commentHeartDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(7);
    }

    @Test
    void testExecCommentHeartDeleteWithUserDAO() {
        Long check = 1L;
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(8);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(check, commentHeartDAO, userDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(7);
    }

    @Test
    void testExecReplyHeart() {
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(5);

        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(replyHeartDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecReplyHeartWithUserDAO() {
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(5);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(replyHeartDAO, userDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecReplyHeartDelete() {
        Long check = 1L;
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(8);

        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(check, replyHeartDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(7);
    }

    @Test
    void testExecReplyHeartDeleteWithUserDAO() {
        Long check = 1L;
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        replyHeartDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setSendHeartCount(8);

        UserDAO updatedUserDAO = updateUserSendHeartDAOBean.exec(check, replyHeartDAO, userDAO);

        Assertions.assertThat(updatedUserDAO.getSendHeartCount()).isEqualTo(7);
    }
}