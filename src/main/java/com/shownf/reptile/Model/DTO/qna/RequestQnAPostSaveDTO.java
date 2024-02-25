package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnAPostSaveDTO {
    Long userId;
    String title;
    String content;
    String imageUrl;
}
