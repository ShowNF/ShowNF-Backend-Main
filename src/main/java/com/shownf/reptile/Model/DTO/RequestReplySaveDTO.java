package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestReplySaveDTO {
    Long commentId;
    Long userId;
    String content;
}
