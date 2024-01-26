package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetUserDAOBeanTest {

    private final UserRepositoryJPA userRepositoryJPA = mock(UserRepositoryJPA.class);
    private final GetUserDAOBean getUserDAOBean = new GetUserDAOBean(userRepositoryJPA);

    @Test
    void exec_withUserId_shouldReturnUser() {
        // Given
        Long userId = 1L;
        UserDAO expectedUser = new UserDAO();
        expectedUser.setUserId(userId);

        // Mock the behavior of UserRepositoryJPA
        when(userRepositoryJPA.findById(userId))
                .thenReturn(Optional.of(expectedUser));

        // When
        UserDAO result = getUserDAOBean.exec(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void exec_withNonExistentUserId_shouldReturnNull() {
        // Given
        Long userId = 2L;

        // Mock the behavior of UserRepositoryJPA
        when(userRepositoryJPA.findById(userId))
                .thenReturn(Optional.empty());

        // When
        UserDAO result = getUserDAOBean.exec(userId);

        // Then
        assertThat(result).isNull();
    }
}