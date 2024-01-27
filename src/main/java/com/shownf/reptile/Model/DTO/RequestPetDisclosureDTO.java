package com.shownf.reptile.Model.DTO;

import lombok.Data;

@Data
public class RequestPetDisclosureDTO {
    Long petId;
    Long userId;
    String disclosure;
}
