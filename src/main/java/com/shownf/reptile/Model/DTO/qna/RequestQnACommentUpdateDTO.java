package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnACommentUpdateDTO {
    Long qnaCommentId;
    Long qnaPostId;
    Long userId;
    String content;
    String imageUrl;
}
