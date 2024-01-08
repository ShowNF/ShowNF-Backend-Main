package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestReplyUpdateDTO {
    Long replyId;
    Long userId;
    String content;
}
