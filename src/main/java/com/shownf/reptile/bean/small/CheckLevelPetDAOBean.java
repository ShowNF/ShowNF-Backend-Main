package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.Enum.Level;
import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.stereotype.Component;

@Component
public class CheckLevelPetDAOBean {

    // Check the pet's level based on level experience.
    public PetDAO exec(PetDAO petDAO){

        // 경험치
        Integer levelExperience = petDAO.getLevelExperience();

        if (levelExperience >= 840) petDAO.setLevel(Level.LEVEL_10);
        else if (levelExperience >= 700) petDAO.setLevel(Level.LEVEL_9);
        else if (levelExperience >= 560) petDAO.setLevel(Level.LEVEL_8);
        else if (levelExperience >= 420) petDAO.setLevel(Level.LEVEL_7);
        else if (levelExperience >= 280) petDAO.setLevel(Level.LEVEL_6);
        else if (levelExperience >= 210) petDAO.setLevel(Level.LEVEL_5);
        else if (levelExperience >= 140) petDAO.setLevel(Level.LEVEL_4);
        else if (levelExperience >= 70) petDAO.setLevel(Level.LEVEL_3);
        else if (levelExperience >= 30) petDAO.setLevel(Level.LEVEL_2);
        else petDAO.setLevel(Level.LEVEL_1);

        return petDAO;
    }
}
