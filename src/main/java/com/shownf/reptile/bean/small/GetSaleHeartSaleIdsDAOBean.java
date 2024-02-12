package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.SaleHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetSaleHeartSaleIdsDAOBean {

    // 좋아요 누른 분양글 아이디 가져오기
    public List<Long> exec(List<SaleHeartDAO> saleHeartDAOS){
        List<Long> saleHeartIds = new ArrayList<>();

        for (SaleHeartDAO saleHeartDAO : saleHeartDAOS)
            saleHeartIds.add(saleHeartDAO.getSaleId());

        return saleHeartIds;
    }
}