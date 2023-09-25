package com.shownf.reptile.Model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDAO {
    private String senderId;
    private String senderName;
    private String content;
    private long timestamp;
}