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
    private String senderId;
    private String senderName;
    private String content;
    private long timestamp;
}