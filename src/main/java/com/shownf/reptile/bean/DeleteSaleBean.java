package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestSaleDeleteDTO;
import com.shownf.reptile.Model.entity.SaleDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CheckUserAccessTokenDAOBean;
import com.shownf.reptile.bean.small.GetSaleDAOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteSaleBean {

    GetSaleDAOBean getSaleDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;

    @Autowired
    public DeleteSaleBean(GetSaleDAOBean getSaleDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean) {
        this.getSaleDAOBean = getSaleDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
    }


    // 분양글 삭제
    public Long exec(RequestSaleDeleteDTO requestSaleDeleteDTO, HttpServletRequest request){

        // 삭제할 분양글 찾기
        SaleDAO saleDAO = getSaleDAOBean.exec(requestSaleDeleteDTO.getSaleId());
        if (saleDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestSaleDeleteDTO.getUserId());
        if (userDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return null;

        // 분양글 deleteCheck 값 true 설정
        saleDAO.setDeleteCheck(true);

        // 유저 분양글 수 감소

        // 경험치 감소


        return 0L;
    }
}
