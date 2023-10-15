package com.shownf.reptile.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseChatDTO {
    private String chatId;
    private String contentId;
    private Long senderId;
    private String senderName;
    private String content;
    private long timestamp;
}