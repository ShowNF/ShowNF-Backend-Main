package com.shownf.reptile.bean.small;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateUniqueIdBeanTest {

    @Autowired
    private CreateUniqueIdBean createUniqueIdBean;

    @Test
    void exec() {
        // When
        long result1 = createUniqueIdBean.exec();
        long result2 = createUniqueIdBean.exec();

        // Then
        assertThat(result1).isNotEqualTo(result2);
    }
}