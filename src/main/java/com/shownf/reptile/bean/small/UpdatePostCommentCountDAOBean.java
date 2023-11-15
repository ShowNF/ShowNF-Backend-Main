package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        PostDAO postDAO = postRepositoryJPA.findById(postId).orElse(null);
        if (postDAO == null)
            return null;

        // 게시물 댓글 수 1 증가
        postDAO.setCommentCount(postDAO.getCommentCount() + 1);

        // 게시물 반환
        return postDAO;
    }

    // 대댓글 삭제시 게시물 댓글 갯수 감소
    public PostDAO exec(Long replyId, Long postId){

        // postId 로 게시물 찾기
        PostDAO postDAO = postRepositoryJPA.findById(postId).orElse(null);
        if (postDAO == null)
            return null;

        postDAO.setCommentCount(postDAO.getCommentCount() - 1);

        // 게시물 반환
        return postDAO;
    }

    // 게시물 댓글 갯수 감소
    public PostDAO exec(Long commentId, CommentDAO commentDAO){

        // postId 가져오기
        Long postId = commentDAO.getPostId();

        // postId 로 게시물 찾기
        PostDAO postDAO = postRepositoryJPA.findById(postId).orElse(null);
        if (postDAO == null)
            return null;

        // 게시물 댓글 수 감소
        postDAO.setCommentCount(postDAO.getCommentCount() - 1);

        /*// 댓글 삭제시 대댓글 갯수까지 감소
        CommentDAO newCommentDAO = commentRepositoryJPA.findById(commentId).orElse(null);
        if (newCommentDAO == null)
            return null;
        Integer replyCount = newCommentDAO.getReplyCount();

        if (replyCount == 0)
            postDAO.setCommentCount(postDAO.getCommentCount() - 1);
        else
            postDAO.setCommentCount(postDAO.getCommentCount() - 1 - replyCount);*/

        // 게시물 반환
        return postDAO;
    }
}
