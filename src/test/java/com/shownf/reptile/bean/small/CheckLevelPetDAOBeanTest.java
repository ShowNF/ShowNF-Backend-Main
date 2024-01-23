package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.Enum.Level;
import com.shownf.reptile.Model.entity.PetDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class CheckLevelPetDAOBeanTest {

    @Autowired
    CheckLevelPetDAOBean checkLevelPetDAOBean;

    @Test
    void exec_Level1() {
        // 테스트할 데이터 생성
        PetDAO petDAO = new PetDAO();
        petDAO.setLevelExperience(10); // 경험치가 LEVEL_1에 해당하는 값

        // 테스트 실행
        PetDAO resultPetDAO = checkLevelPetDAOBean.exec(petDAO);

        // 결과 검증
        assertThat(resultPetDAO.getLevel()).isEqualTo(Level.LEVEL_1);
    }

    @Test
    void exec_Level5() {
        // 테스트할 데이터 생성
        PetDAO petDAO = new PetDAO();
        petDAO.setLevelExperience(300); // 경험치가 LEVEL_6에 해당하는 값

        // 테스트 실행
        PetDAO resultPetDAO = checkLevelPetDAOBean.exec(petDAO);

        // 결과 검증
        assertThat(resultPetDAO.getLevel()).isEqualTo(Level.LEVEL_6);
    }

    @Test
    void exec_Level10() {
        // 테스트할 데이터 생성
        PetDAO petDAO = new PetDAO();
        petDAO.setLevelExperience(1000); // 경험치가 LEVEL_10에 해당하는 값

        // 테스트 실행
        PetDAO resultPetDAO = checkLevelPetDAOBean.exec(petDAO);

        // 결과 검증
        assertThat(resultPetDAO.getLevel()).isEqualTo(Level.LEVEL_10);
    }
}