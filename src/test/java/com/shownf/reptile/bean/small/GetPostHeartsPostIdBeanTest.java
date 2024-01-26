package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetPostHeartsPostIdBeanTest {

    @Autowired
    GetPostHeartsPostIdBean getPostHeartsPostIdBean;

    @Test
    void exec_withPostHearts_shouldReturnPostIds() {
        // Given
        List<PostHeartDAO> postHeartDAOs = new ArrayList<>();
        postHeartDAOs.add(new PostHeartDAO(1L, 1L, 1L, null));
        postHeartDAOs.add(new PostHeartDAO(2L, 2L, 2L, null));

        // When
        List<Long> result = getPostHeartsPostIdBean.exec(postHeartDAOs);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).contains(1L, 2L);
    }

    @Test
    void exec_withEmptyList_shouldReturnEmptyList() {
        // Given
        List<PostHeartDAO> postHeartDAOs = new ArrayList<>();

        // When
        List<Long> result = getPostHeartsPostIdBean.exec(postHeartDAOs);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}