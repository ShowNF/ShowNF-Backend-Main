package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UpdatePostHeartCountDAOBeanTest {

    @InjectMocks
    private UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean;

    @Mock
    private GetPostDAOBean getPostDAOBean;

    @Mock
    private GetPostMetaDAOBean getPostMetaDAOBean;

    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdatePostHeartCountIncrease() {
        // 가짜 데이터 설정
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setPostId(1L);

        PostDAO postDAO = new PostDAO();
        postDAO.setPostId(1L);
        postDAO.setHeartCount(5);

        // Mockito 설정
        when(getPostDAOBean.exec(1L)).thenReturn(postDAO);

        // 테스트 대상 메소드 호출
        PostDAO updatedPostDAO = updatePostHeartCountDAOBean.exec(postHeartDAO);

        // 검증
        assertEquals(6, updatedPostDAO.getHeartCount());
        verify(getPostDAOBean, times(1)).exec(1L);
    }

    @Test
    void testUpdatePostHeartCountDecrease() {
        // 가짜 데이터 설정
        PostHeartDAO postHeartDAO = new PostHeartDAO();
        postHeartDAO.setPostId(1L);

        PostDAO postDAO = new PostDAO();
        postDAO.setPostId(1L);
        postDAO.setHeartCount(5);

        // Mockito 설정
        when(getPostDAOBean.exec(1L)).thenReturn(postDAO);

        // 테스트 대상 메소드 호출
        PostDAO updatedPostDAO = updatePostHeartCountDAOBean.exec(1L, postHeartDAO);

        // 검증
        assertEquals(4, updatedPostDAO.getHeartCount());
        verify(getPostDAOBean, times(1)).exec(1L);
    }

    @Test
    void testUpdatePostMetaHeartCount() {
        // 가짜 데이터 설정
        PostDAO postDAO = new PostDAO();
        postDAO.setPostId(1L);
        postDAO.setHeartCount(5);

        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(1L);

        // Mockito 설정
        when(getPostMetaDAOBean.exec(1L)).thenReturn(postMeta);

        // 테스트 대상 메소드 호출
        PostMeta updatedPostMeta = updatePostHeartCountDAOBean.exec(postDAO);

        // 검증
        assertEquals(5, updatedPostMeta.getHeartCount());
        verify(getPostMetaDAOBean, times(1)).exec(1L);
    }
}
