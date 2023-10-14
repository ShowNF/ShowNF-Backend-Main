package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class ResponseUserDTO {
    Long handleId;
    String userId;
    String name;
    String image;
}
