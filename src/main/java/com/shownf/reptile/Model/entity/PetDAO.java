package com.shownf.reptile.Model.entity;

import com.shownf.reptile.Model.Enum.Disclosure;
import com.shownf.reptile.Model.Enum.Gender;
import com.shownf.reptile.Model.Enum.Level;
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
public class PetDAO {
    @Id
    Long petId;
    Long userId;
    String imageUrl;
    String name;
    String firstSpecies;
    String secondSpecies;
    String morph;
    String birthday;
    Double weight;
    Gender gender;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer diaryCount;
    Level level;
    Integer levelExperience;
    Disclosure disclosure;
    boolean deleteCheck;
}
