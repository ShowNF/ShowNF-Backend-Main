package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class RequestSaleUpdateDTO {
    Long saleId;
    Long petId;
    Long userId;
    List<Map<String, String>> imageUrl;
    String firstSpecies;
    String secondSpecies;
    String morph;
    String birthday;
    Double weight;
    String memo;
    Integer price;
    String salePlatform;
    Boolean cites;
    String area;
    String region;
    String gender;
}
