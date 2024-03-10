package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class ResponseRecommendQnAPostGetDTO {
    Long qnaPostId;
    String title;
    Integer commentCount;
    Integer viewCount;
    Integer heartCount;
}
