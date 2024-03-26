package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleHeartDeleteDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteSaleHeartBean {

    GetSaleHeartDAOBean getSaleHeartDAOBean;
    CheckSaleIdSaleDAOBean checkSaleIdSaleDAOBean;
    CheckUserIdSaleDAOBean checkUserIdSaleDAOBean;
    DeleteSaleHeartDAOBean deleteSaleHeartDAOBean;
    UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean;
    SaveSaleDAOBean saveSaleDAOBean;

    @Autowired
    public DeleteSaleHeartBean(GetSaleHeartDAOBean getSaleHeartDAOBean, CheckSaleIdSaleDAOBean checkSaleIdSaleDAOBean, CheckUserIdSaleDAOBean checkUserIdSaleDAOBean, DeleteSaleHeartDAOBean deleteSaleHeartDAOBean, UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean, SaveSaleDAOBean saveSaleDAOBean) {
        this.getSaleHeartDAOBean = getSaleHeartDAOBean;
        this.checkSaleIdSaleDAOBean = checkSaleIdSaleDAOBean;
        this.checkUserIdSaleDAOBean = checkUserIdSaleDAOBean;
        this.deleteSaleHeartDAOBean = deleteSaleHeartDAOBean;
        this.updateSaleHeartCountDAOBean = updateSaleHeartCountDAOBean;
        this.saveSaleDAOBean = saveSaleDAOBean;
    }

    // 분양글 좋아요 삭제
    public Long exec(RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO){

        // 분양글 좋아요 중복 배제
        SaleHeartDAO saleHeartDAO = getSaleHeartDAOBean.exec(requestSaleHeartDeleteDTO.getUserId(), requestSaleHeartDeleteDTO.getSaleId());
        if (saleHeartDAO == null) return 0L;

        // 분양글 좋아요 해당하는 분양글 확인
        if (!checkSaleIdSaleDAOBean.exec(saleHeartDAO, requestSaleHeartDeleteDTO))
            return null;

        // 분양글 좋아요 해당하는 유저 확인
        if (!checkUserIdSaleDAOBean.exec(saleHeartDAO, requestSaleHeartDeleteDTO))
            return null;

        // 분양글 좋아요 갯수 감소
        SaleDAO saleDAO = updateSaleHeartCountDAOBean.exec(0L, saleHeartDAO);

        // 좋아요 삭제
        deleteSaleHeartDAOBean.exec(saleHeartDAO);

        // 분양글 저장
        saveSaleDAOBean.exec(saleDAO);

        // saleId 반환
        return saleDAO.getSaleId();
    }
}
