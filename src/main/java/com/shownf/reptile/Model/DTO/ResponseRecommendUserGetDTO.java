package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class ResponseRecommendUserGetDTO {
    Long userId;
    String userName;
    String imageUrl;
}
