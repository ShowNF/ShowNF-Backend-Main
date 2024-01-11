package com.shownf.reptile.Model.DTO;

import com.shownf.reptile.Model.Enum.Achievement;
import com.shownf.reptile.Model.Enum.Grade;
import lombok.Data;

@Data
public class ResponseAchievementDTO {
    Achievement achievement;
    String achievementName;
    String icon;
    String style;
    String achievementExplain;
    Integer score;
    String grade;
}
