package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetPostHeartPostIdsDAOBeanTest {

    @Autowired
    GetPostHeartPostIdsDAOBean getPostHeartPostIdsDAOBean;

    @Test
    void exec_withPostHeartDAOS_shouldReturnPostIds() {
        // Given
        List<PostHeartDAO> postHeartDAOS = new ArrayList<>();
        postHeartDAOS.add(new PostHeartDAO(1L, 1L, 101L, null));
        postHeartDAOS.add(new PostHeartDAO(2L, 2L, 102L, null));
        postHeartDAOS.add(new PostHeartDAO(3L, 3L, 103L, null));

        // When
        List<Long> result = getPostHeartPostIdsDAOBean.exec(postHeartDAOS);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result).contains(1L, 2L, 3L);
    }

    @Test
    void exec_withEmptyPostHeartDAOS_shouldReturnEmptyList() {
        // Given
        List<PostHeartDAO> emptyPostHeartDAOS = new ArrayList<>();

        // When
        List<Long> result = getPostHeartPostIdsDAOBean.exec(emptyPostHeartDAOS);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}