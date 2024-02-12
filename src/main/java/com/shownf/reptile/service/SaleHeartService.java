package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestSaleHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestSaleHeartSaveDTO;
import com.shownf.reptile.bean.DeleteSaleHeartBean;
import com.shownf.reptile.bean.GetSaleIdsBean;
import com.shownf.reptile.bean.SaveSaleHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleHeartService {

    GetSaleIdsBean getSaleIdsBean;
    SaveSaleHeartBean saveSaleHeartBean;
    DeleteSaleHeartBean deleteSaleHeartBean;

    @Autowired
    public SaleHeartService(GetSaleIdsBean getSaleIdsBean, SaveSaleHeartBean saveSaleHeartBean, DeleteSaleHeartBean deleteSaleHeartBean) {
        this.getSaleIdsBean = getSaleIdsBean;
        this.saveSaleHeartBean = saveSaleHeartBean;
        this.deleteSaleHeartBean = deleteSaleHeartBean;
    }

    // 좋아요 누른 전체 분양글 아이디 가져오기
    public List<Long> getSaleIds(Long userId){
        return getSaleIdsBean.exec(userId);
    }

    // 분양글 좋아요 저장
    public Long saveSaleHeart(RequestSaleHeartSaveDTO requestSaleHeartSaveDTO){
        return saveSaleHeartBean.exec(requestSaleHeartSaveDTO);
    }

    // 분양글 좋아요 삭제
    public Long deleteSaleHeart(RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO){
        return deleteSaleHeartBean.exec(requestSaleHeartDeleteDTO);
    }
}
