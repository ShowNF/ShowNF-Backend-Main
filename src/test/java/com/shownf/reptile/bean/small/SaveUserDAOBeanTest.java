package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestSiteUserUpdateDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SaveUserDAOBeanTest {

    @Mock
    private UserRepositoryJPA userRepositoryJPA;

    @InjectMocks
    private SaveUserDAOBean saveUserDAOBean;

    @Test
    public void testSaveUserDAO() {
        MockitoAnnotations.initMocks(this);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);

        // Test saving user
        saveUserDAOBean.exec(userDAO);

        // Verify that userRepositoryJPA's save method was called once with the correct argument
        verify(userRepositoryJPA, times(1)).save(userDAO);
    }

    @Test
    public void testUpdateUserSiteInfo() {
        MockitoAnnotations.initMocks(this);

        UserDAO userDAO = new UserDAO();
        userDAO.setUserId(1L);

        RequestSiteUserUpdateDTO requestSiteUserUpdateDTO = new RequestSiteUserUpdateDTO();
        requestSiteUserUpdateDTO.setSiteImage("newImageURL");
        requestSiteUserUpdateDTO.setSiteName("newSiteName");

        // Test updating user site info
        saveUserDAOBean.exec(userDAO, requestSiteUserUpdateDTO);

        // Verify that userRepositoryJPA's save method was called once with the correct argument
        verify(userRepositoryJPA, times(1)).save(userDAO);

        // Verify that userDAO's siteImage and siteName were updated correctly
        assertEquals("newImageURL", userDAO.getSiteImage());
        assertEquals("newSiteName", userDAO.getSiteName());
    }
}
