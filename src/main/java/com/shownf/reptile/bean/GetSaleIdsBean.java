package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.SaleHeartDAO;
import com.shownf.reptile.bean.small.GetSaleHeartSaleIdsDAOBean;
import com.shownf.reptile.bean.small.GetSaleHeartsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSaleIdsBean {

    GetSaleHeartsDAOBean getSaleHeartsDAOBean;
    GetSaleHeartSaleIdsDAOBean getSaleHeartSaleIdsDAOBean;

    @Autowired
    public GetSaleIdsBean(GetSaleHeartsDAOBean getSaleHeartsDAOBean, GetSaleHeartSaleIdsDAOBean getSaleHeartSaleIdsDAOBean) {
        this.getSaleHeartsDAOBean = getSaleHeartsDAOBean;
        this.getSaleHeartSaleIdsDAOBean = getSaleHeartSaleIdsDAOBean;
    }

    // 좋아요 누른 분양글 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 좋아요 누른 분양글 가져오기
        List<SaleHeartDAO> saleHeartDAOs = getSaleHeartsDAOBean.exec(userId);

        // 좋아요에서 분양글 id list 가져오기
        return getSaleHeartSaleIdsDAOBean.exec(saleHeartDAOs);
    }
}
