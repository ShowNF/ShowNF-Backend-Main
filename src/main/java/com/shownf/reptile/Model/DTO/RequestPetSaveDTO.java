package com.shownf.reptile.Model.DTO;

import com.shownf.reptile.Model.Enum.Gender;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RequestPetSaveDTO {
    Long userId;
    private List<Map<String, String>> imageUrl;
    String name;
    String firstSpecies;
    String secondSpecies;
    String morph;
    String birthday;
    Double weight;
    String gender;
}
