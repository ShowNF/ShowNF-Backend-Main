package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdatePostCommentCountDAOBeanTest {

    @Mock
    private PostRepositoryJPA postRepositoryJPA;

    @Mock
    private PostMetaRepositoryJPA postMetaRepositoryJPA;

    @InjectMocks
    private UpdatePostCommentCountDAOBean updatePostCommentCountDAOBean;

    @Test
    void testIncrementCommentCount() {
        // 가짜 데이터 설정
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setPostId(1L);

        PostDAO postDAO = new PostDAO();
        postDAO.setCommentCount(10);

        // Mock 설정
        when(postRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(postDAO));

        // 테스트 대상 메소드 호출
        PostDAO updatedPostDAO = updatePostCommentCountDAOBean.exec(commentDAO);

        // 검증
        assertEquals(11, updatedPostDAO.getCommentCount());
    }

    @Test
    void testDecrementCommentCount() {
        // 가짜 데이터 설정
        CommentDAO commentDAO = new CommentDAO();
        commentDAO.setPostId(1L);

        PostDAO postDAO = new PostDAO();
        postDAO.setCommentCount(10);

        // Mock 설정
        when(postRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(postDAO));

        // 테스트 대상 메소드 호출
        PostDAO updatedPostDAO = updatePostCommentCountDAOBean.exec(1L, commentDAO);

        // 검증
        assertEquals(9, updatedPostDAO.getCommentCount());
    }

    @Test
    void testUpdatePostMetaCommentCount() {
        // 가짜 데이터 설정
        PostDAO postDAO = new PostDAO();
        postDAO.setPostId(1L);
        postDAO.setCommentCount(10);

        PostMeta postMeta = new PostMeta();
        postMeta.setPostId(1L);

        // Mock 설정
        when(postMetaRepositoryJPA.findById(anyLong())).thenReturn(Optional.of(postMeta));

        // 테스트 대상 메소드 호출
        PostMeta updatedPostMeta = updatePostCommentCountDAOBean.exec(postDAO);

        // 검증
        assertEquals(10, updatedPostMeta.getCommentCount());
    }
}
