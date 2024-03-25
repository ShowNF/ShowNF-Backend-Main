package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleUpdateDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdateSaleBean {

    GetSaleDAOBean getSaleDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateSaleDAOBean updateSaleDAOBean;
    SaveSaleDAOBean saveSaleDAOBean;

    @Autowired
    public UpdateSaleBean(GetSaleDAOBean getSaleDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateSaleDAOBean updateSaleDAOBean, SaveSaleDAOBean saveSaleDAOBean) {
        this.getSaleDAOBean = getSaleDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateSaleDAOBean = updateSaleDAOBean;
        this.saveSaleDAOBean = saveSaleDAOBean;
    }

    // Update the sale
    public Long exec(RequestSaleUpdateDTO requestSaleUpdateDTO, HttpServletRequest request){

        // 분양글 찾기
        SaleDAO saleDAO = getSaleDAOBean.exec(requestSaleUpdateDTO.getSaleId());
        if (saleDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestSaleUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // 분양글 수정
        updateSaleDAOBean.exec(saleDAO, requestSaleUpdateDTO);

        // 분양글 저장
        saveSaleDAOBean.exec(saleDAO);

        return saleDAO.getSaleId();
    }
}