package com.shownf.reptile.Model.entity;

import com.shownf.reptile.Model.Enum.ChatCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatRoomDAO {
    @Id
    Long uniqueId;
    String chatRoomId;
    Long sellerId;
    Long buyerId;
    ChatCategory category;
    String content;
    Long contentUserId;
    LocalDateTime contentUploadTime;
    LocalDateTime uploadTime;
}
