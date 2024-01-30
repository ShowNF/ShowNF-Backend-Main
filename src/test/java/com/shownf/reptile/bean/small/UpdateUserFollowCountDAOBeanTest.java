package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UpdateUserFollowCountDAOBeanTest {

    @Mock
    private GetUserDAOBean getUserDAOBean;

    @Mock
    private SaveUserDAOBean saveUserDAOBean;

    @InjectMocks
    private UpdateUserFollowCountDAOBean updateUserFollowCountDAOBean;

    @Test
    void testExecFollow() {
        // 가짜 데이터 설정
        FollowDAO followDAO = new FollowDAO();
        followDAO.setUserId(1L);
        followDAO.setFollowUserId(2L);

        UserDAO userDAO = new UserDAO();
        userDAO.setFollowerCount(10);

        UserDAO followedUserDAO = new UserDAO();
        followedUserDAO.setFollowingCount(8);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);
        Mockito.when(getUserDAOBean.exec(2L)).thenReturn(followedUserDAO);

        // 테스트 대상 메소드 호출
        updateUserFollowCountDAOBean.exec(followDAO);

        // 호출 여부 검증
        Mockito.verify(saveUserDAOBean, Mockito.times(2)).exec(Mockito.any(UserDAO.class));

        assertThat(userDAO.getFollowerCount()).isEqualTo(11);
        assertThat(followedUserDAO.getFollowingCount()).isEqualTo(9);
    }

    @Test
    void testExecUnfollow() {
        // 가짜 데이터 설정
        FollowDAO followDAO = new FollowDAO();
        followDAO.setUserId(1L);
        followDAO.setFollowUserId(2L);

        UserDAO userDAO = new UserDAO();
        userDAO.setFollowerCount(10);

        UserDAO followedUserDAO = new UserDAO();
        followedUserDAO.setFollowingCount(8);

        // Mockito를 사용하여 getUserDAOBean.exec 호출 시 반환할 가짜 값 설정
        Mockito.when(getUserDAOBean.exec(1L)).thenReturn(userDAO);
        Mockito.when(getUserDAOBean.exec(2L)).thenReturn(followedUserDAO);

        // 테스트 대상 메소드 호출
        updateUserFollowCountDAOBean.exec(1L, followDAO);

        // 호출 여부 검증
        Mockito.verify(saveUserDAOBean, Mockito.times(2)).exec(Mockito.any(UserDAO.class));

        assertThat(userDAO.getFollowerCount()).isEqualTo(9);
        assertThat(followedUserDAO.getFollowingCount()).isEqualTo(7);
    }
}
