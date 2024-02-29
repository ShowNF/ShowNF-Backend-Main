package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseQnAReplyGetDTO {
    Long qnaReplyId;
    Long qnaCommentId;
    Long userId;
    String content;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
}
