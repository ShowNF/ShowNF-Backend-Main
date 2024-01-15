package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestPostContentUpdateDTO {
    Long postContentId;
    String imageUrl;
    String content;
}
