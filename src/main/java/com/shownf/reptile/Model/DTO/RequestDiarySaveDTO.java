package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestDiarySaveDTO {
    Long petId;
    String imageUrl;
    String food;
    Integer foodCounter;
    String size;
    Double weight;
    String memo;
    String date;
}
