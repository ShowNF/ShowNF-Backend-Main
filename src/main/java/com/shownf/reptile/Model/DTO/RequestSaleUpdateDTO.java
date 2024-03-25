package com.shownf.reptile.Model.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestSaleUpdateDTO {
    Long saleId;
    Long petId;
    Long userId;
    String imageUrl;
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
    LocalDateTime updateTime;
}
