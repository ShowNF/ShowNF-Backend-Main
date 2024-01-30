package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateUserPetCountDAOBeanTest {

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @InjectMocks
    private UpdateUserPetCountDAOBean updateUserPetCountDAOBean;

    @Test
    void testExecPetAdd() {
        // 가짜 데이터 설정
        PetDAO petDAO = new PetDAO();
        petDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setPetCount(3);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserPetCountDAOBean.exec(petDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        assertEquals(4, updatedUserDAO.getPetCount());
    }

    @Test
    void testExecPetDelete() {
        // 가짜 데이터 설정
        PetDAO petDAO = new PetDAO();
        petDAO.setUserId(1L);

        UserDAO userDAO = new UserDAO();
        userDAO.setPetCount(3);
        userDAO.setDiaryCount(10);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);

        // 테스트 대상 메소드 호출
        UserDAO updatedUserDAO = updateUserPetCountDAOBean.exec(5, petDAO);

        // 호출 여부 검증
        Mockito.verify(getUserDAOBean, Mockito.times(1)).exec(1L);

        // 검증
        assertEquals(2, updatedUserDAO.getPetCount());
        assertEquals(5, updatedUserDAO.getDiaryCount());
    }
}
