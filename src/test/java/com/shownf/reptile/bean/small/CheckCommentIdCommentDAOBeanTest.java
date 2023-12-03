package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplyDeleteDTO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CheckCommentIdCommentDAOBeanTest {

    @Autowired CheckCommentIdCommentDAOBean checkCommentIdCommentDAOBean;

    // 좋아요 commentId 판별
    @Test
    void exec1_1() {
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO = new RequestCommentHeartDeleteDTO();
        commentHeartDAO.setCommentId(1L);
        requestCommentHeartDeleteDTO.setCommentId(1L);

        boolean test = checkCommentIdCommentDAOBean.exec(commentHeartDAO, requestCommentHeartDeleteDTO);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void exec1_2() {
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO = new RequestCommentHeartDeleteDTO();
        commentHeartDAO.setCommentId(2L);
        requestCommentHeartDeleteDTO.setCommentId(1L);

        boolean test = checkCommentIdCommentDAOBean.exec(commentHeartDAO, requestCommentHeartDeleteDTO);

        assertThat(test).isEqualTo(false);
    }

    // 대댓글 commentId 판별
    @Test
    void exec2_1() {
        ReplyDAO replyDAO = new ReplyDAO();
        RequestReplyDeleteDTO requestReplyDeleteDTO = new RequestReplyDeleteDTO();
        replyDAO.setCommentId(1L);
        requestReplyDeleteDTO.setCommentId(1L);

        boolean test = checkCommentIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO);

        assertThat(test).isEqualTo(true);
    }

    @Test
    void exec2_2() {
        ReplyDAO replyDAO = new ReplyDAO();
        RequestReplyDeleteDTO requestReplyDeleteDTO = new RequestReplyDeleteDTO();
        replyDAO.setCommentId(1L);
        requestReplyDeleteDTO.setCommentId(2L);

        boolean test = checkCommentIdCommentDAOBean.exec(replyDAO, requestReplyDeleteDTO);

        assertThat(test).isEqualTo(false);
    }
}