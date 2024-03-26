package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleHeartSaveDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
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
    GetUserDAOBean getUserDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveSaleHeartBean(GetSaleHeartDAOBean getSaleHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreateSaleHeartDAOBean createSaleHeartDAOBean, SaveSaleHeartDAOBean saveSaleHeartDAOBean, UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean, SaveSaleDAOBean saveSaleDAOBean, GetUserDAOBean getUserDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getSaleHeartDAOBean = getSaleHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createSaleHeartDAOBean = createSaleHeartDAOBean;
        this.saveSaleHeartDAOBean = saveSaleHeartDAOBean;
        this.updateSaleHeartCountDAOBean = updateSaleHeartCountDAOBean;
        this.saveSaleDAOBean = saveSaleDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 분양글 좋아요 저장
    public Long exec(RequestSaleHeartSaveDTO requestSaleHeartSaveDTO){

        // 분양글 좋아요 중복 배제
        if (getSaleHeartDAOBean.exec(requestSaleHeartSaveDTO.getUserId(), requestSaleHeartSaveDTO.getSaleId()) != null) return 0L;

        // saleHeartId 생성
        Long saleHeartId = createUniqueIdBean.exec();

        // DTO 객체 DAO 변환
        SaleHeartDAO saleHeartDAO = createSaleHeartDAOBean.exec(saleHeartId, requestSaleHeartSaveDTO);

        // 분양글 좋아요 갯수 추가
        SaleDAO saleDAO = updateSaleHeartCountDAOBean.exec(saleHeartDAO);

        // 좋아요 receiver 추가
        UserDAO receiverUserDAO = getUserDAOBean.exec(saleDAO.getUserId());
        if (receiverUserDAO == null) return 0L;
        updateUserReceiveHeartDAOBean.exec(receiverUserDAO);

        // 좋아요 sender 추가
        UserDAO senderUserDAO = getUserDAOBean.exec(requestSaleHeartSaveDTO.getUserId());
        if (senderUserDAO == null) return 0L;

        if (requestSaleHeartSaveDTO.getUserId().equals(receiverUserDAO.getUserId()))
             updateUserSendHeartDAOBean.exec(receiverUserDAO);
        else updateUserSendHeartDAOBean.exec(senderUserDAO);

        // 유저 경험치 추가
        updateUserExpDAOBean.exec(senderUserDAO, requestSaleHeartSaveDTO);

        // 분양글 좋아요 저장
        saveSaleHeartDAOBean.exec(saleHeartDAO);

        // 분양글 저장
        saveSaleDAOBean.exec(saleDAO);

        // 유저 저장
        saveUserDAOBean.exec(receiverUserDAO);
        saveUserDAOBean.exec(senderUserDAO);

        // saleHeartId 반환
        return saleHeartId;
    }
}
