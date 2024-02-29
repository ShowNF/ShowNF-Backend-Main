package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnAReplySaveDTO {
    Long qnaCommentId;
    Long userId;
    String content;
}
