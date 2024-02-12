package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseSaleDTO {
    Long saleId;
    Long petId;
    Long userId;
    String imageUrl;
    String individual;
    String firstSpecies;
    String secondSpecies;
    String birthday;
    Double weight;
    String memo;
    Integer price;
    Integer heartCount;
    String salePlatform;
    String link;
    String refundPolicy;
    Boolean cites;
    String area;
    String region;
    String gender;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
}
