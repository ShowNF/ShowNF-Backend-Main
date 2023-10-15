package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseChatRoomDTO {
    Long uniqueId;
    String chatRoomId;
    Long sellerId;
    Long buyerId;
    LocalDateTime uploadTime;
}
