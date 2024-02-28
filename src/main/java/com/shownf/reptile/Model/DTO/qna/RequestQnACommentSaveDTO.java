package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnACommentSaveDTO {
    Long qnaPostId;
    Long userId;
    String content;
    String imageUrl;
}
