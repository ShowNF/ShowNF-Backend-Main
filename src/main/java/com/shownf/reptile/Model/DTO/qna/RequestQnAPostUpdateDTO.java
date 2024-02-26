package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnAPostUpdateDTO {
    Long qnaPostId;
    Long userId;
    String title;
    String content;
    String imageUrl;
}
