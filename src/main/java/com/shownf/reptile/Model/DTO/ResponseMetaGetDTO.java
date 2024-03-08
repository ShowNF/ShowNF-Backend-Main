package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class ResponseMetaGetDTO {
    Long metaId;
    Long userId;
    String imageUrl;
    String title;
}
