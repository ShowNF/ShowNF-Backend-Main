package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseCommentsDTO {
    Long commentId;
    Long postId;
    Long userId;
    String content;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer replyCount;
}
