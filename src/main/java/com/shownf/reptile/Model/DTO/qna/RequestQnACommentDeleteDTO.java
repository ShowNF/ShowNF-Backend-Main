package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnACommentDeleteDTO {
    Long qnaCommentId;
    Long qnaPostId;
    Long userId;
}
