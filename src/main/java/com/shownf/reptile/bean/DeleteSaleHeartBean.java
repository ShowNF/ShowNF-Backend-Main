package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleHeartDeleteDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.SaleHeartDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteSaleHeartBean {

    GetSaleHeartDAOBean getSaleHeartDAOBean;
    CheckSaleIdSaleDAOBean checkSaleIdSaleDAOBean;
    CheckUserIdSaleDAOBean checkUserIdSaleDAOBean;
    DeleteSaleHeartDAOBean deleteSaleHeartDAOBean;
    UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean;
    SaveSaleDAOBean saveSaleDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeleteSaleHeartBean(GetSaleHeartDAOBean getSaleHeartDAOBean, CheckSaleIdSaleDAOBean checkSaleIdSaleDAOBean, CheckUserIdSaleDAOBean checkUserIdSaleDAOBean, DeleteSaleHeartDAOBean deleteSaleHeartDAOBean, UpdateSaleHeartCountDAOBean updateSaleHeartCountDAOBean, SaveSaleDAOBean saveSaleDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getSaleHeartDAOBean = getSaleHeartDAOBean;
        this.checkSaleIdSaleDAOBean = checkSaleIdSaleDAOBean;
        this.checkUserIdSaleDAOBean = checkUserIdSaleDAOBean;
        this.deleteSaleHeartDAOBean = deleteSaleHeartDAOBean;
        this.updateSaleHeartCountDAOBean = updateSaleHeartCountDAOBean;
        this.saveSaleDAOBean = saveSaleDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 분양글 좋아요 삭제
    public Long exec(RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO, HttpServletRequest request){

        // 분양글 좋아요 중복 배제
        SaleHeartDAO saleHeartDAO = getSaleHeartDAOBean.exec(requestSaleHeartDeleteDTO.getUserId(), requestSaleHeartDeleteDTO.getSaleId());
        if (saleHeartDAO == null) return 0L;

        // 유저 댓글 토큰으로 확인
        UserDAO senderUserDAO = getUserDAOBean.exec(requestSaleHeartDeleteDTO.getUserId());
        if (senderUserDAO == null) return 0L;
        if (!checkUserAccessTokenDAOBean.exec(senderUserDAO, request))
            return null;

        // 분양글 좋아요 갯수 감소
        SaleDAO saleDAO = updateSaleHeartCountDAOBean.exec(0L, saleHeartDAO);

        // 좋아요 sender, receiver 감소
        // 좋아요 sender, receiver 감소
        UserDAO reveiverUserDAO = getUserDAOBean.exec(saleDAO.getUserId());
        if (reveiverUserDAO == null) return 0L;
        updateUserReceiveHeartDAOBean.exec(null, reveiverUserDAO);

        updateUserSendHeartDAOBean.exec(0L, senderUserDAO);

        // 경험치 삭제
        updateUserExpDAOBean.exec(senderUserDAO, requestSaleHeartDeleteDTO);

        // 좋아요 삭제
        deleteSaleHeartDAOBean.exec(saleHeartDAO);

        // 분양글 저장
        saveSaleDAOBean.exec(saleDAO);

        // 유저 저장
        saveUserDAOBean.exec(senderUserDAO);
        saveUserDAOBean.exec(reveiverUserDAO);

        // saleId 반환
        return saleDAO.getSaleId();
    }
}
