package com.shownf.reptile.Model.DTO;

import com.shownf.reptile.Model.Enum.Level;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponsePetDTO {
    Long petId;
    Long userId;
    String imageUrl;
    String name;
    String firstSpecies;
    String secondSpecies;
    String birthday;
    Double weight;
    String gender;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer diaryCount;
    Level level;
    Integer levelExperience;
}
