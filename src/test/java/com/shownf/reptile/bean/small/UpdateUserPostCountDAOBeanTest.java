package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateUserPostCountDAOBeanTest {

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @InjectMocks
    private UpdateUserPostCountDAOBean updateUserPostCountDAOBean;

    @Test
    void testExecPostSave() {
        // 가짜 데이터 설정
        RequestPostSaveDTO requestPostSaveDTO = new RequestPostSaveDTO();
        requestPostSaveDTO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setPostCount(5);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserPostCountDAOBean.exec(requestPostSaveDTO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        assertEquals(6, updatedUserDAO.getPostCount());
    }

    @Test
    void testExecPostDelete() {
        // 가짜 데이터 설정
        RequestPostDeleteDTO requestPostDeleteDTO = new RequestPostDeleteDTO();
        requestPostDeleteDTO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setPostCount(8);
        userDAO.setReceiveHeartCount(20);
        userDAO.setCommentCount(15);

        PostDAO postDAO = new PostDAO();
        postDAO.setHeartCount(5);
        postDAO.setCommentCount(3);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserPostCountDAOBean.exec(requestPostDeleteDTO, postDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        assertEquals(7, updatedUserDAO.getPostCount());
        assertEquals(15, updatedUserDAO.getReceiveHeartCount());
        assertEquals(12, updatedUserDAO.getCommentCount());
    }
}
