package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestSaleHeartDeleteDTO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CheckUserIdSaleDAOBeanTest {

    @Mock
    private RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO;

    @InjectMocks
    private CheckUserIdSaleDAOBean checkUserIdSaleDAOBean;

    @Test
    public void testCheckUserIdForSaleHeartDAO() {
        MockitoAnnotations.initMocks(this);

        SaleHeartDAO saleHeartDAO = new SaleHeartDAO();
        saleHeartDAO.setUserId(1L);

        when(requestSaleHeartDeleteDTO.getUserId()).thenReturn(1L);

        assertTrue(checkUserIdSaleDAOBean.exec(saleHeartDAO, requestSaleHeartDeleteDTO));
    }
}
