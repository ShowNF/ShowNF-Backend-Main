package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateUserCommentCountDAOBeanTest {

    @Mock
    private GetCommentDAOBean getCommentDAOBean;

    @Mock
    private GetPostDAOBean getPostDAOBean;

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @InjectMocks
    private UpdateUserCommentCountDAOBean updateUserCommentCountDAOBean;

    public UpdateUserCommentCountDAOBeanTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecAddCommentCountForCommentSave() {
        // 가짜 데이터 설정
        RequestCommentSaveDTO requestCommentSaveDTO = new RequestCommentSaveDTO();
        requestCommentSaveDTO.setPostId(1L);

        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(100L);

        UserDAO userDAO = new UserDAO();
        userDAO.setCommentCount(10);

        // Mockito를 사용하여 getPostDAOBean.exec(postId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getPostDAOBean.exec(1L)).thenReturn(postDAO);

        // Mockito를 사용하여 getUserDAOBean.exec(userId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getUserDAOBean.exec(100L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserCommentCountDAOBean.exec(requestCommentSaveDTO);

        // 검증
        assertEquals(11, updatedUserDAO.getCommentCount());
    }

    @Test
    void testExecDecreaseCommentCountForCommentDelete() {
        // 가짜 데이터 설정
        RequestCommentDeleteDTO requestCommentDeleteDTO = new RequestCommentDeleteDTO();
        requestCommentDeleteDTO.setPostId(1L);

        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(100L);

        UserDAO userDAO = new UserDAO();
        userDAO.setCommentCount(10);

        // Mockito를 사용하여 getPostDAOBean.exec(postId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getPostDAOBean.exec(1L)).thenReturn(postDAO);

        // Mockito를 사용하여 getUserDAOBean.exec(userId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getUserDAOBean.exec(100L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserCommentCountDAOBean.exec(requestCommentDeleteDTO);

        // 검증
        assertEquals(9, updatedUserDAO.getCommentCount());
    }

    @Test
    void testExecAddCommentCountForReplySave() {
        // 가짜 데이터 설정
        RequestReplySaveDTO requestReplySaveDTO = new RequestReplySaveDTO();
        requestReplySaveDTO.setCommentId(1L);

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setPostId(2L);

        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(100L);

        UserDAO userDAO = new UserDAO();
        userDAO.setCommentCount(10);

        // Mockito를 사용하여 getCommentDAOBean.exec(commentId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getCommentDAOBean.exec(1L)).thenReturn(commentDAO);

        // Mockito를 사용하여 getPostDAOBean.exec(postId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getPostDAOBean.exec(2L)).thenReturn(postDAO);

        // Mockito를 사용하여 getUserDAOBean.exec(userId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getUserDAOBean.exec(100L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserCommentCountDAOBean.exec(requestReplySaveDTO);

        // 검증
        assertEquals(11, updatedUserDAO.getCommentCount());
    }

    @Test
    void testExecDecreaseCommentCountForReplyDelete() {
        // 가짜 데이터 설정
        RequestReplyDeleteDTO requestReplyDeleteDTO = new RequestReplyDeleteDTO();
        requestReplyDeleteDTO.setCommentId(1L);

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setPostId(2L);

        PostDAO postDAO = new PostDAO();
        postDAO.setUserId(100L);

        UserDAO userDAO = new UserDAO();
        userDAO.setCommentCount(10);

        // Mockito를 사용하여 getCommentDAOBean.exec(commentId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getCommentDAOBean.exec(1L)).thenReturn(commentDAO);

        // Mockito를 사용하여 getPostDAOBean.exec(postId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getPostDAOBean.exec(2L)).thenReturn(postDAO);

        // Mockito를 사용하여 getUserDAOBean.exec(userId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getUserDAOBean.exec(100L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserCommentCountDAOBean.exec(requestReplyDeleteDTO);

        // 검증
        assertEquals(9, updatedUserDAO.getCommentCount());
    }
}
