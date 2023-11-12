package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePostCommentCountDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    CommentRepositoryJPA commentRepositoryJPA;

    @Autowired
    public UpdatePostCommentCountDAOBean(PostRepositoryJPA postRepositoryJPA, CommentRepositoryJPA commentRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.commentRepositoryJPA = commentRepositoryJPA;
    }

    // 게시물 댓글 갯수 추가
    public PostDAO exec(CommentDAO commentDAO){

        // postId 가져오기
        Long postId = commentDAO.getPostId();

        // postId 로 게시물 찾기
        PostDAO postDAO = postRepositoryJPA.findById(postId).get();

        // 게시물 댓글 수 1 증가
        postDAO.setCommentCount(postDAO.getCommentCount() + 1);

        // 게시물 반환
        return postDAO;
    }

    // 게시물 댓글 갯수 감소
    public PostDAO exec(Long commentId, CommentDAO commentDAO){

        // postId 가져오기
        Long postId = commentDAO.getPostId();

        // postId 로 게시물 찾기
        PostDAO postDAO = postRepositoryJPA.findById(postId).get();

        // 게시물 댓글 수 감소
        Integer replyCount = commentRepositoryJPA.findById(commentId).get().getReplyCount();
        if (replyCount == 0)
            postDAO.setCommentCount(postDAO.getCommentCount() - 1);
        else
            postDAO.setCommentCount(postDAO.getCommentCount() - 1 - replyCount);

        // 게시물 반환
        return postDAO;
    }
}
