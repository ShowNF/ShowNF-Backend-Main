package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestFollowDTO {
    Long userId;
    Long followUserId;
}
