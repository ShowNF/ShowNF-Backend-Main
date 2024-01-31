package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.SaleHeartDAO;
import com.shownf.reptile.repository.SaleHeartRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DeleteSaleHeartDAOBeanTest {

    @Mock
    private SaleHeartRepositoryJPA saleHeartRepositoryJPA;

    @InjectMocks
    private DeleteSaleHeartDAOBean deleteSaleHeartDAOBean;

    @Test
    public void testDeleteSaleHeartDAO() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // 테스트에 사용할 더미 SaleHeartDAO 생성
        SaleHeartDAO saleHeartDAO = new SaleHeartDAO();

        // 테스트 대상 메서드 호출
        deleteSaleHeartDAOBean.exec(saleHeartDAO);

        // 특정 메서드가 호출되었는지 검증
        verify(saleHeartRepositoryJPA, times(1)).delete(saleHeartDAO);
    }
}
