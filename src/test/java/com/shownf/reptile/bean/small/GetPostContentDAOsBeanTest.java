package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.repository.PostContentRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetPostContentDAOsBeanTest {

    @Mock
    private PostContentRepositoryJPA postContentRepositoryJPA;

    @InjectMocks
    private GetPostContentDAOsBean getPostContentDAOsBean;

    @Test
    void exec_withPostId_shouldReturnPostContentDAOList() {
        // Given
        Long postId = 1L;
        List<PostContentDAO> expectedPostContentDAOList = new ArrayList<>();
        expectedPostContentDAOList.add(new PostContentDAO());

        // Mock the behavior of PostContentRepositoryJPA
        when(postContentRepositoryJPA.findByPostId(postId)).thenReturn(expectedPostContentDAOList);

        // When
        List<PostContentDAO> result = getPostContentDAOsBean.exec(postId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedPostContentDAOList);

        // Verify that the findByPostId method of PostContentRepositoryJPA was called with the correct argument
        verify(postContentRepositoryJPA, times(1)).findByPostId(postId);
    }

    @Test
    void exec_withPostIdAndContent_shouldReturnSortedContent() {
        // Given
        Long postId = 1L;
        String content = "[{\"0\":1},{\"1\":2}]";
        List<PostContentDAO> expectedPostContentDAOList = new ArrayList<>();
        expectedPostContentDAOList.add(new PostContentDAO(1L, 1L, "image1.jpg", "Content 1", 0, false));
        expectedPostContentDAOList.add(new PostContentDAO(2L, 1L, "image2.jpg", "Content 2", 1, false));

        // Mock the behavior of PostContentRepositoryJPA
        when(postContentRepositoryJPA.findByPostId(postId)).thenReturn(expectedPostContentDAOList);

        // When
        String result = getPostContentDAOsBean.exec(postId, content);

        // Then
        assertThat(result).isNotNull();

        // Verify that the findByPostId method of PostContentRepositoryJPA was called with the correct argument
        verify(postContentRepositoryJPA, times(1)).findByPostId(postId);

        // Verify that the content is sorted based on postContentIndex
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, String>> sortedContents = objectMapper.readValue(result, new TypeReference<List<Map<String, String>>>() {});
            assertThat(sortedContents).isNotNull();
            assertThat(sortedContents).hasSize(2);
            assertThat(sortedContents.get(0).get("postContentIndex")).isEqualTo("0");
            assertThat(sortedContents.get(1).get("postContentIndex")).isEqualTo("1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}