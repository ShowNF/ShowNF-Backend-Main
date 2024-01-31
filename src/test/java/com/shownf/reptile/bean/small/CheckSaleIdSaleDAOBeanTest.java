package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestSaleHeartDeleteDTO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckSaleIdSaleDAOBeanTest {

    @Test
    public void testCheckSaleId() {
        CheckSaleIdSaleDAOBean checkSaleIdSaleDAOBean = new CheckSaleIdSaleDAOBean();

        // 테스트에 사용할 더미 SaleHeartDAO 및 RequestSaleHeartDeleteDTO 생성
        SaleHeartDAO saleHeartDAO = new SaleHeartDAO();
        saleHeartDAO.setSaleId(1L);

        RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO = new RequestSaleHeartDeleteDTO();
        requestSaleHeartDeleteDTO.setSaleId(1L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertTrue(checkSaleIdSaleDAOBean.exec(saleHeartDAO, requestSaleHeartDeleteDTO));
    }

    @Test
    public void testCheckSaleIdMismatch() {
        CheckSaleIdSaleDAOBean checkSaleIdSaleDAOBean = new CheckSaleIdSaleDAOBean();

        // 테스트에 사용할 더미 SaleHeartDAO 및 RequestSaleHeartDeleteDTO 생성
        SaleHeartDAO saleHeartDAO = new SaleHeartDAO();
        saleHeartDAO.setSaleId(1L);

        RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO = new RequestSaleHeartDeleteDTO();
        requestSaleHeartDeleteDTO.setSaleId(2L);

        // 테스트 대상 메서드 호출 및 결과 검증
        assertFalse(checkSaleIdSaleDAOBean.exec(saleHeartDAO, requestSaleHeartDeleteDTO));
    }
}
