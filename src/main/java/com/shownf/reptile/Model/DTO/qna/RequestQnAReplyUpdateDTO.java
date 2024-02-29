package com.shownf.reptile.Model.DTO.qna;

import lombok.Data;

@Data
public class RequestQnAReplyUpdateDTO {
    Long qnaReplyId;
    Long userId;
    String content;
}
