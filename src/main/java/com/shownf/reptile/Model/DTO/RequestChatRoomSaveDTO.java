package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestChatRoomSaveDTO {
    String chatRoomId;
    Long sellerId;
    Long buyerId;
    String category;
}
