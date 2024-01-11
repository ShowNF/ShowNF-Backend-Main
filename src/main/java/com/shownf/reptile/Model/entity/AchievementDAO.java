package com.shownf.reptile.Model.entity;

import com.shownf.reptile.Model.Enum.Achievement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AchievementDAO {
    @Id
    Achievement achievement;
    String achievementName;
    String icon;
    String style;
    String achievementExplain;
    Integer bronze;
    Integer silver;
    Integer gold;
    Integer platinum;
    Integer diamond;
}
