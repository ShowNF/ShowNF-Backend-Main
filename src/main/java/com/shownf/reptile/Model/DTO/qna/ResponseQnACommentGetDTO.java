package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseQnACommentGetDTO {
    Long qnaCommentId;
    Long qnaPostId;
    Long userId;
    String content;
    String imageUrl;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer replyCount;
    Integer selectionCount;
    boolean selection;
}
