package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestCommentSaveDTO {
    Long postId;
    Long userId;
    String content;
}
