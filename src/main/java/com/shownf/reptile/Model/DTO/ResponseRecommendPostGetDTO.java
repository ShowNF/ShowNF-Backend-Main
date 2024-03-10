package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class ResponseRecommendPostDTO {
    Long postId;
    Long userId;
    String title;
    String imageUrl;
}
