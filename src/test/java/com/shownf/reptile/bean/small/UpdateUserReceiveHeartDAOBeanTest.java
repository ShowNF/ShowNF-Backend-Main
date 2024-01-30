package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UpdateUserReceiveHeartDAOBeanTest {

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @InjectMocks
    private UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;

    @Test
    void testExecPostSave() {
        // 가짜 데이터 설정
        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setReceiveHeartCount(5);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserReceiveHeartDAOBean.exec(postDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        assertThat(updatedUserDAO.getReceiveHeartCount()).isEqualTo(6);
    }

    @Test
    void testExecPostDelete() {
        // 가짜 데이터 설정
        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setReceiveHeartCount(8);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserReceiveHeartDAOBean.exec(1L, postDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        assertThat(updatedUserDAO.getReceiveHeartCount()).isEqualTo(7);
    }

    @Test
    void testExecCommentSave() {
        // 가짜 데이터 설정
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setReceiveHeartCount(10);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserReceiveHeartDAOBean.exec(commentDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        Assertions.assertThat(updatedUserDAO.getReceiveHeartCount()).isEqualTo(11);
    }

    @Test
    void testExecCommentDelete() {
        // 가짜 데이터 설정
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setReceiveHeartCount(15);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserReceiveHeartDAOBean.exec(1L, commentDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        Assertions.assertThat(updatedUserDAO.getReceiveHeartCount()).isEqualTo(14);
    }

    @Test
    void testExecReplySave() {
        // 가짜 데이터 설정
        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setReceiveHeartCount(20);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserReceiveHeartDAOBean.exec(replyDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        Assertions.assertThat(updatedUserDAO.getReceiveHeartCount()).isEqualTo(21);
    }

    @Test
    void testExecReplyDelete() {
        // 가짜 데이터 설정
        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setReceiveHeartCount(25);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserReceiveHeartDAOBean.exec(1L, replyDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        Assertions.assertThat(updatedUserDAO.getReceiveHeartCount()).isEqualTo(24);
    }
}