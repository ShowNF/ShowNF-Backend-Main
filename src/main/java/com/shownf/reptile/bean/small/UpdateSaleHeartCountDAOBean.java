package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSaleHeartCountDAOBean {

    GetSaleDAOBean getSaleDAOBean;

    @Autowired
    public UpdateSaleHeartCountDAOBean(GetSaleDAOBean getSaleDAOBean) {
        this.getSaleDAOBean = getSaleDAOBean;
    }

    // 분양글 좋아요 갯수 추가
    public SaleDAO exec(SaleHeartDAO saleHeartDAO){

        // saleId 가져오기
        Long saleId = saleHeartDAO.getSaleId();

        // saleId 로 분양글 찾기
        SaleDAO saleDAO = getSaleDAOBean.exec(saleId);
        if (saleDAO == null) return null;

        // 분양글 좋아요 수 1 증가
        saleDAO.setHeartCount(saleDAO.getHeartCount() + 1);

        // 분양글 반환
        return saleDAO;
    }

    // 분양글 좋아요 갯수 감소
    public SaleDAO exec(Long saleHeartId, SaleHeartDAO saleHeartDAO){

        // saleId 가져오기
        Long saleId = saleHeartDAO.getSaleId();

        // saleId 로 분양글 찾기
        SaleDAO saleDAO = getSaleDAOBean.exec(saleId);
        if (saleDAO == null) return null;

        // 분양글 좋아요 수 1 감소
        saleDAO.setHeartCount(saleDAO.getHeartCount() - 1);

        // 분양글 반환
        return saleDAO;
    }
}
