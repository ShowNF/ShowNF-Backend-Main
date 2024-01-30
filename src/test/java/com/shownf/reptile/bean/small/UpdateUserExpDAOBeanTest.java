package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.Model.entity.*;
import com.shownf.reptile.config.UserExpConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateUserExpDAOBeanTest {

    @Mock
    private UserExpConfig userExpConfig;

    @Autowired
    private UpdateUserExpDAOBean updateUserExpDAOBean;

    public UpdateUserExpDAOBeanTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecPostSave() {
        // 가짜 데이터 설정
        RequestPostSaveDTO requestPostSaveDTO = new RequestPostSaveDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);

        // Mockito를 사용하여 userExpConfig.getPost() 호출 시 반환할 가짜 값 설정
        Mockito.when(userExpConfig.getPost()).thenReturn(6);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(requestPostSaveDTO, userDAO);

        // 검증
        assertEquals(56, updatedUserDAO.getExp());
    }

    @Test
    void testExecPostDelete() {
        // 가짜 데이터 설정
        RequestPostDeleteDTO requestPostDeleteDTO = new RequestPostDeleteDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);

        // Mockito를 사용하여 userExpConfig.getPost() 호출 시 반환할 가짜 값 설정
        Mockito.when(userExpConfig.getPost()).thenReturn(6);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(requestPostDeleteDTO, userDAO);

        // 검증
        assertEquals(44, updatedUserDAO.getExp());
    }

    @Test
    void testExecCommentSave() {
        // Setup
        RequestCommentSaveDTO requestCommentSaveDTO = new RequestCommentSaveDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getComment()).thenReturn(3);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(requestCommentSaveDTO, userDAO);

        // Verify
        assertEquals(53, updatedUserDAO.getExp());
    }

    @Test
    void testExecCommentDelete() {
        // Setup
        RequestCommentDeleteDTO requestCommentDeleteDTO = new RequestCommentDeleteDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getComment()).thenReturn(3);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(requestCommentDeleteDTO, userDAO);

        // Verify
        assertEquals(47, updatedUserDAO.getExp());
    }

    @Test
    void testExecReplySave() {
        // Setup
        RequestReplySaveDTO requestReplySaveDTO = new RequestReplySaveDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getReply()).thenReturn(3);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(requestReplySaveDTO, userDAO);

        // Verify
        assertEquals(53, updatedUserDAO.getExp());
    }

    @Test
    void testExecReplyDelete() {
        // Setup
        RequestReplyDeleteDTO requestReplyDeleteDTO = new RequestReplyDeleteDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getReply()).thenReturn(3);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(requestReplyDeleteDTO, userDAO);

        // Verify
        assertEquals(47, updatedUserDAO.getExp());
    }

    @Test
    void testExecPostHeartSave() {
        // Setup
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getHeart()).thenReturn(1);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(postHeartDAO, userDAO);

        // Verify
        assertEquals(51, updatedUserDAO.getExp());
    }

    @Test
    void testExecPostHeartDelete() {
        // Setup
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getHeart()).thenReturn(1);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec("check", postHeartDAO, userDAO);

        // Verify
        assertEquals(49, updatedUserDAO.getExp());
    }

    @Test
    void testExecCommentHeartSave() {
        // Setup
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getHeart()).thenReturn(1);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(commentHeartDAO, userDAO);

        // Verify
        assertEquals(51, updatedUserDAO.getExp());
    }

    @Test
    void testExecCommentHeartDelete() {
        // Setup
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getHeart()).thenReturn(1);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec("check", commentHeartDAO, userDAO);

        // Verify
        assertEquals(49, updatedUserDAO.getExp());
    }

    @Test
    void testExecReplyHeartSave() {
        // Setup
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getHeart()).thenReturn(1);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec(replyHeartDAO, userDAO);

        // Verify
        assertEquals(51, updatedUserDAO.getExp());
    }

    @Test
    void testExecReplyHeartDelete() {
        // Setup
        ReplyHeartDAO replyHeartDAO = new ReplyHeartDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.setExp(50);
        Mockito.when(userExpConfig.getHeart()).thenReturn(1);

        // Test
        UserDAO updatedUserDAO = updateUserExpDAOBean.exec("check", replyHeartDAO, userDAO);

        // Verify
        assertEquals(49, updatedUserDAO.getExp());
    }

}
