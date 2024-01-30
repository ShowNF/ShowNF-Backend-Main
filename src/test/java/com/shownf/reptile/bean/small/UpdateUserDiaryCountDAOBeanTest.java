package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateUserDiaryCountDAOBeanTest {

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @InjectMocks
    private UpdateUserDiaryCountDAOBean updateUserDiaryCountDAOBean;

    public UpdateUserDiaryCountDAOBeanTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecAddDiaryCountForDiarySave() {
        // 가짜 데이터 설정
        PetDAO petDAO = new PetDAO();
        petDAO.setUserId(100L);

        UserDAO userDAO = new UserDAO();
        userDAO.setDiaryCount(5);

        // Mockito를 사용하여 getUserDAOBean.exec(userId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getUserDAOBean.exec(100L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserDiaryCountDAOBean.exec(petDAO);

        // 검증
        assertEquals(6, updatedUserDAO.getDiaryCount());
    }

    @Test
    void testExecDecreaseDiaryCountForDiaryDelete() {
        // 가짜 데이터 설정
        PetDAO petDAO = new PetDAO();
        petDAO.setUserId(100L);

        UserDAO userDAO = new UserDAO();
        userDAO.setDiaryCount(5);

        // Mockito를 사용하여 getUserDAOBean.exec(userId) 호출 시 반환할 가짜 객체 설정
        Mockito.when(getUserDAOBean.exec(100L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserDiaryCountDAOBean.exec(1L, petDAO);

        // 검증
        assertEquals(4, updatedUserDAO.getDiaryCount());
    }
}
