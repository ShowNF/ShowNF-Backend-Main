package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestDiaryUpdateDTO {
    Long diaryId;
    Long petId;
    String imageUrl;
    String food;
    Integer foodCounter;
    String size;
    Double weight;
    String memo;
    String date;
    boolean ecdysis;
    boolean cleaning;
    boolean shower;
    boolean bowelMovement;
}
