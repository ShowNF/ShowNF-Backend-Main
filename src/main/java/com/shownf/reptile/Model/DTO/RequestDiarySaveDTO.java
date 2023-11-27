package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestDiarySaveDTO {
    Long petId;
    Long diaryId;
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
