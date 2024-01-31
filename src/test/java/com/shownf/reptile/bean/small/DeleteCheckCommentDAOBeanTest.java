package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeleteCheckCommentDAOBeanTest {

    @Autowired
    private DeleteCheckCommentDAOBean deleteCheckCommentDAOBean;

    @Test
    public void testDeleteCheck() {
        // Create a list of CommentDAO objects for testing
        List<CommentDAO> commentDAOs = new ArrayList<>();
        commentDAOs.add(new CommentDAO(1L, 1L, 10L, "Comment 1", null, null, 0, 5, false));
        commentDAOs.add(new CommentDAO(2L, 1L, 11L, "Comment 2", null, null, 0, 0, true));
        commentDAOs.add(new CommentDAO(3L, 1L, 12L, "Comment 3", null, null, 0, 3, true));

        // Execute the delete check
        List<CommentDAO> result = deleteCheckCommentDAOBean.exec(commentDAOs);

        // Validate the changes
        assertEquals(2, result.size());

        // Check that the second comment is marked as deleted and modified accordingly
        CommentDAO deletedComment = result.get(1);
        assertEquals("삭제된 댓글입니다", deletedComment.getContent());
        assertEquals(0L, deletedComment.getUserId());
        assertEquals(0, deletedComment.getHeartCount());
        assertEquals(0, deletedComment.getReplyCount());

        // Check that other comments remain unchanged
        CommentDAO comment1 = result.get(0);
        assertEquals("Comment 1", comment1.getContent());
    }
}
