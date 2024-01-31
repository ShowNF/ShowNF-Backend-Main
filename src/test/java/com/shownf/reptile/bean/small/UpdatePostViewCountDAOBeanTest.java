package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UpdatePostViewCountDAOBeanTest {

    @InjectMocks
    private UpdatePostViewCountDAOBean updatePostViewCountDAOBean;

    @Mock
    private GetPostDAOBean getPostDAOBean;

    @Mock
    private GetPostMetaDAOBean getPostMetaDAOBean;

    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdatePostViewCountDAOBeanForPostDAO() {
        // 가짜 데이터 설정
        PostDAO postDAO = new PostDAO();
        postDAO.setPostId(1L);
        postDAO.setViewCount(5);

        // Mockito 설정
        when(getPostDAOBean.exec(1L)).thenReturn(postDAO);

        // 테스트 대상 메소드 호출
        PostDAO updatedPostDAO = updatePostViewCountDAOBean.exec(postDAO);

        // 검증
        assertEquals(6, updatedPostDAO.getViewCount());
        verify(getPostDAOBean, times(1)).exec(1L);
    }

    @Test
    void testUpdatePostViewCountDAOBeanForPostMeta() {
        // 가짜 데이터 설정
        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(1L);
        postMeta.setViewCount(5);

        // Mockito 설정
        when(getPostMetaDAOBean.exec(1L)).thenReturn(postMeta);

        // 테스트 대상 메소드 호출
        PostMeta updatedPostMeta = updatePostViewCountDAOBean.exec(postMeta);

        // 검증
        assertEquals(6, updatedPostMeta.getViewCount());
        verify(getPostMetaDAOBean, times(1)).exec(1L);
    }
}
