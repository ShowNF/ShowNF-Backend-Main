package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SaleService {

    GetSaleBean getSaleBean;
    GetSalesBean getSalesBean;
    GetUserSaleHeartsBean getUserSaleHeartsBean;
    SaveSaleBean saveSaleBean;
    UpdatePetUserNameBean updatePetUserNameBean;
    UpdateSaleBean updateSaleBean;
    DeleteSaleBean deleteSaleBean;

    @Autowired
    public SaleService(GetSaleBean getSaleBean, GetSalesBean getSalesBean, GetUserSaleHeartsBean getUserSaleHeartsBean, SaveSaleBean saveSaleBean, UpdatePetUserNameBean updatePetUserNameBean, UpdateSaleBean updateSaleBean, DeleteSaleBean deleteSaleBean) {
        this.getSaleBean = getSaleBean;
        this.getSalesBean = getSalesBean;
        this.getUserSaleHeartsBean = getUserSaleHeartsBean;
        this.saveSaleBean = saveSaleBean;
        this.updatePetUserNameBean = updatePetUserNameBean;
        this.updateSaleBean = updateSaleBean;
        this.deleteSaleBean = deleteSaleBean;
    }

    // 분양글 조회
    public ResponseSaleDTO getSale(Long saleId){
        return getSaleBean.exec(saleId);
    }

    // 마이페이지 유저 분양글 전체 조회
    public Page<ResponseSaleDTO> getSales(Long userId, Pageable pageable){
        return getSalesBean.exec(userId, pageable);
    }

    // 분양글 Page 로 전체 조회
    public Page<ResponseSaleDTO> getSales(Pageable pageable){
        return getSalesBean.exec(pageable);
    }

    // 유저가 좋아요한 분양글 조회
    public List<ResponseSaleDTO> getUserSaleHearts(Long userId){
        return getUserSaleHeartsBean.exec(userId);
    }

    // 분양글 저장
    public Long saveSale(RequestSaleSaveDTO requestSaleSaveDTO){
        return saveSaleBean.exec(requestSaleSaveDTO);
    }

    // 분양시 펫 userId 변경
    public Long updatePetUserId(RequestSalePetUpdateDTO requestSalePetUpdateDTO, HttpServletRequest request){
        return updatePetUserNameBean.exec(requestSalePetUpdateDTO, request);
    }

    // 분양글 수정
    public Long updateSale(RequestSaleUpdateDTO requestSaleUpdateDTO, HttpServletRequest request){
        return updateSaleBean.exec(requestSaleUpdateDTO, request);
    }

    // 분양글 삭제
    public Long deleteSale(RequestSaleDeleteDTO requestSaleDeleteDTO, HttpServletRequest request){
        return deleteSaleBean.exec(requestSaleDeleteDTO, request);
    }
}
