package com.shownf.reptile.bean.small;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateUniqueNicknameBeanTest {

    @Autowired
    CreateUniqueNicknameBean createUniqueNicknameBean;

    @Test
    void exec() {
        // When
        String result1 = createUniqueNicknameBean.exec();
        String result2 = createUniqueNicknameBean.exec();

        // Then
        assertThat(result1).isNotEqualTo(result2);
        assertThat(result1).startsWith("user");
        assertThat(result2).startsWith("user");
        assertThat(result1).hasSize(10); // "user" + 6 digits
        assertThat(result2).hasSize(10);
    }
}