package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestImageHeartDeleteDTO;
import com.shownf.reptile.Model.entity.ImageHeartDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckUserIdImageDAOBeanTest {

    @Mock
    private RequestImageHeartDeleteDTO requestImageHeartDeleteDTO;

    @InjectMocks
    private CheckUserIdImageDAOBean checkUserIdImageDAOBean;

    @Test
    public void testCheckUserIdForImageHeartDAO() {
        MockitoAnnotations.initMocks(this);

        ImageHeartDAO imageHeartDAO = new ImageHeartDAO();
        imageHeartDAO.setUserId(1L);

        when(requestImageHeartDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdImageDAOBean.exec(imageHeartDAO, requestImageHeartDeleteDTO));
    }
}