package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseQnAPostGetDTO {
    Long qnaPostId;
    Long userId;
    String title;
    String imageUrl;
    String content;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer commentCount;
    Integer viewCount;
}
