package com.shownf.reptile.Model.DTO;

import com.shownf.reptile.Model.Enum.Achievement;
import lombok.Data;

@Data
public class ResponseAchievementDTO {
    Achievement achievement;
    String achievementName;
    String icon;
    String achievementExplain;
    Integer score;
}
