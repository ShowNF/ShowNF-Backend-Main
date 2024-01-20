package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.CommentRepositoryJPA;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdatePostCommentCountDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @Autowired
    public UpdatePostCommentCountDAOBean(PostRepositoryJPA postRepositoryJPA, PostMetaRepositoryJPA postMetaRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
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

        // 게시물 반환
        return postDAO;
    }

    // 게시물메타데이터 게시물 댓글 갯수 추가
    public PostMeta exec(PostDAO postDAO){

        // postId 가져오기
        Long postId = postDAO.getPostId();

        // postId 로 게시물 찾기
        PostMeta postMeta = postMetaRepositoryJPA.findById(postId).orElse(null);
        if (postMeta == null) return null;

        // 게시물 메타데이터 댓글 수 변동
        postMeta.setCommentCount(postDAO.getCommentCount());

        // 게시물 반환
        return postMeta;
    }
}
