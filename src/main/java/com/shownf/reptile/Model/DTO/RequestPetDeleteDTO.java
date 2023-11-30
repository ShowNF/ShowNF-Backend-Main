package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestPetDeleteDTO {
    Long petId;
    Long userId;
}
