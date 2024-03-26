package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleSaveDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveSaleBean {

    CreateUniqueIdBean createUniqueIdBean;
    CreateSaleDAOBean createSaleDAOBean;
    GetUserDAOBean getUserDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveSaleDAOBean saveSaleDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveSaleBean(CreateUniqueIdBean createUniqueIdBean, CreateSaleDAOBean createSaleDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveSaleDAOBean saveSaleDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.createSaleDAOBean = createSaleDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveSaleDAOBean = saveSaleDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 분양글 저장
    public Long exec(RequestSaleSaveDTO requestSaleSaveDTO){

        // saleId 생성
        Long saleId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        SaleDAO saleDAO = createSaleDAOBean.exec(saleId, requestSaleSaveDTO);

        // 분양글 작성 유저 찾기
        UserDAO userDAO = getUserDAOBean.exec(saleDAO.getUserId());
        if (userDAO == null) return null;

        // 분양글 저장 시 유저 분양글 수 증가
        userDAO.setSaleCount(userDAO.getSaleCount() + 1);

        // 경험치 추가
        updateUserExpDAOBean.exec(userDAO, requestSaleSaveDTO);

        // 분양글 저장
        saveSaleDAOBean.exec(saleDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // 분양글 saleId 반환
        return saleId;
    }
}
