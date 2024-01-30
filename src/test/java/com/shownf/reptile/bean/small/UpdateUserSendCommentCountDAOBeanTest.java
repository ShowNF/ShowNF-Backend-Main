package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplySaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UpdateUserSendCommentCountDAOBeanTest {

    @InjectMocks
    private UpdateUserSendCommentCountDAOBean updateUserSendCommentCountDAOBean;

    @Test
    void testExecCommentSave() {
        // 가짜 데이터 설정
        RequestCommentSaveDTO requestCommentSaveDTO = new RequestCommentSaveDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setSendCommentCount(5);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendCommentCountDAOBean.exec(requestCommentSaveDTO, userDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendCommentCount()).isEqualTo(6);
    }

    @Test
    void testExecCommentDelete() {
        // 가짜 데이터 설정
        RequestCommentDeleteDTO requestCommentDeleteDTO = new RequestCommentDeleteDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setSendCommentCount(8);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendCommentCountDAOBean.exec(requestCommentDeleteDTO, userDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendCommentCount()).isEqualTo(7);
    }

    @Test
    void testExecReplySave() {
        // 가짜 데이터 설정
        RequestReplySaveDTO requestReplySaveDTO = new RequestReplySaveDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setSendCommentCount(10);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendCommentCountDAOBean.exec(requestReplySaveDTO, userDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendCommentCount()).isEqualTo(11);
    }

    @Test
    void testExecReplyDelete() {
        // 가짜 데이터 설정
        RequestReplyDeleteDTO requestReplyDeleteDTO = new RequestReplyDeleteDTO();
        UserDAO userDAO = new UserDAO();
        userDAO.setSendCommentCount(15);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserSendCommentCountDAOBean.exec(requestReplyDeleteDTO, userDAO);

        // 검증
        Assertions.assertThat(updatedUserDAO.getSendCommentCount()).isEqualTo(14);
    }
}
