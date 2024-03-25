package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestSaleUpdateDTO;
import com.shownf.reptile.Model.Enum.Area;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.Enum.SalePlatform;
import com.shownf.reptile.Model.entity.SaleDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateSaleDAOBean {

    // 분양글 수정
    public void exec(SaleDAO saleDAO, RequestSaleUpdateDTO requestSaleUpdateDTO){
        saleDAO.setImageUrl(requestSaleUpdateDTO.getImageUrl());
        saleDAO.setFirstSpecies(requestSaleUpdateDTO.getFirstSpecies());
        saleDAO.setSecondSpecies(requestSaleUpdateDTO.getSecondSpecies());
        saleDAO.setMorph(requestSaleUpdateDTO.getMorph());
        saleDAO.setBirthday(requestSaleUpdateDTO.getBirthday());
        saleDAO.setWeight(requestSaleUpdateDTO.getWeight());
        saleDAO.setMemo(requestSaleUpdateDTO.getMemo());
        saleDAO.setPrice(requestSaleUpdateDTO.getPrice());
        saleDAO.setSalePlatform(SalePlatform.valueOf(requestSaleUpdateDTO.getSalePlatform()));
        saleDAO.setCites(requestSaleUpdateDTO.getCites());
        saleDAO.setArea(Area.valueOf(requestSaleUpdateDTO.getArea()));
        saleDAO.setRegion(requestSaleUpdateDTO.getRegion());
        saleDAO.setGender(Gender.valueOf(requestSaleUpdateDTO.getGender()));
        saleDAO.setUpdateTime(LocalDateTime.now());
    }
}
