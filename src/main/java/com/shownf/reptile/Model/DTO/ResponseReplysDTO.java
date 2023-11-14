package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseReplysDTO {
    Long replyId;
    Long commentId;
    Long userId;
    String content;
    LocalDateTime uploadTime;
    Integer heartCount;
}
