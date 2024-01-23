package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetCommentHeartCommentIdsDAOBeanTest {

    @Test
    void exec_shouldReturnCommentIdsList() {
        // Given
        CommentHeartDAO commentHeartDAO1 = new CommentHeartDAO();
        commentHeartDAO1.setCommentId(1L);

        CommentHeartDAO commentHeartDAO2 = new CommentHeartDAO();
        commentHeartDAO2.setCommentId(2L);

        CommentHeartDAO commentHeartDAO3 = new CommentHeartDAO();
        commentHeartDAO3.setCommentId(3L);

        List<CommentHeartDAO> commentHeartDAOS = Arrays.asList(commentHeartDAO1, commentHeartDAO2, commentHeartDAO3);

        // When
        GetCommentHeartCommentIdsDAOBean getCommentHeartCommentIdsDAOBean = new GetCommentHeartCommentIdsDAOBean();
        List<Long> result = getCommentHeartCommentIdsDAOBean.exec(commentHeartDAOS);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(1L, 2L, 3L);
    }
}