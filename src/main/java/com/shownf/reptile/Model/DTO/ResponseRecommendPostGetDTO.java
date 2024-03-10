package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class ResponseRecommendPostGetDTO {
    Long postId;
    Long userId;
    String title;
    String imageUrl;
}
