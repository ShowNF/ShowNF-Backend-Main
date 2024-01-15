package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestDiaryUpdateDTO {
    Long diaryId;
    Long petId;
    private List<Map<String, String>> imageUrl;
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
