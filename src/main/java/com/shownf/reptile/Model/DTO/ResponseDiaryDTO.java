package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDiaryDTO {
    Long diaryId;
    String imageUrl;
    Long petId;
    String food;
    Integer foodCounter;
    String size;
    Double weight;
    String memo;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    String date;
    String month;
    boolean ecdysis;
    boolean cleaning;
    boolean shower;
    boolean bowelMovement;
}
