package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestCommentUpdateDTO {
    Long commentId;
    Long userId;
    String content;
}
