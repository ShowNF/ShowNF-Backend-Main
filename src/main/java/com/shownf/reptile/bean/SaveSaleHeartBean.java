package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleHeartSaveDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveSaleHeartBean {

    GetSaleHeartDAOBean getSaleHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreateSaleHeartDAOBean createSaleHeartDAOBean;
    SaveSaleHeartDAOBean saveSaleHeartDAOBean;
    UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean;
    SaveSaleDAOBean saveSaleDAOBean;

    @Autowired
    public SaveSaleHeartBean(GetSaleHeartDAOBean getSaleHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateSaleHeartDAOBean createSaleHeartDAOBean, SaveSaleHeartDAOBean saveSaleHeartDAOBean, UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean, SaveSaleDAOBean saveSaleDAOBean) {
        this.getSaleHeartDAOBean = getSaleHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createSaleHeartDAOBean = createSaleHeartDAOBean;
        this.saveSaleHeartDAOBean = saveSaleHeartDAOBean;
        this.updateSaleHeartCountDAOBean = updateSaleHeartCountDAOBean;
        this.saveSaleDAOBean = saveSaleDAOBean;
    }

    // 분양글 좋아요 저장
    public Long exec(RequestSaleHeartSaveDTO requestSaleHeartSaveDTO){

        // 분양글 좋아요 중복 배제
        if (getSaleHeartDAOBean.exec(requestSaleHeartSaveDTO.getUserId(), requestSaleHeartSaveDTO.getSaleId()) != null) return 0L;

        // saleHeartId 생성
        Long saleHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        SaleHeartDAO saleHeartDAO = createSaleHeartDAOBean.exec(saleHeartId, requestSaleHeartSaveDTO);

        // 분양글 좋아요 저장
        saveSaleHeartDAOBean.exec(saleHeartDAO);

        // 분양글 좋아요 갯수 추가
        SaleDAO saleDAO = updateSaleHeartCountDAOBean.exec(saleHeartDAO);

        // 이미지 저장
        saveSaleDAOBean.exec(saleDAO);

        // saleHeartId 반환
        return saleHeartId;
    }
}
