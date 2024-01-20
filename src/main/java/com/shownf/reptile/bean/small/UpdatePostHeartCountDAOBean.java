package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePostHeartCountDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @Autowired
    public UpdatePostHeartCountDAOBean(PostRepositoryJPA postRepositoryJPA, PostMetaRepositoryJPA postMetaRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
    }

    // 게시물 좋아요 갯수 추가
    public PostDAO exec(PostHeartDAO postHeartDAO){

        // postId 가져오기
        Long postId = postHeartDAO.getPostId();

        // postId 로 게시물 찾기
        PostDAO postDAO = postRepositoryJPA.findById(postId).orElse(null);
        if (postDAO == null) return null;

        // 게시물 좋아요 수 1 증가
        postDAO.setHeartCount(postDAO.getHeartCount() + 1);

        // 게시물 반환
        return postDAO;
    }

    // 게시물 좋아요 갯수 감소
    public PostDAO exec(Long check, PostHeartDAO postHeartDAO){

        // postId 가져오기
        Long postId = postHeartDAO.getPostId();

        // postId 로 게시물 찾기
        PostDAO postDAO = postRepositoryJPA.findById(postId).orElse(null);
        if (postDAO == null) return null;

        // 게시물 좋아요 수 1 감소
        postDAO.setHeartCount(postDAO.getHeartCount() - 1);

        // 게시물 반환
        return postDAO;
    }

    // 게시물메타데이터 게시물 좋아요 갯수 변동
    public PostMeta exec(PostDAO postDAO){

        // postId 가져오기
        Long postId = postDAO.getPostId();

        // postId 로 게시물 찾기
        PostMeta postMeta = postMetaRepositoryJPA.findById(postId).orElse(null);
        if (postMeta == null) return null;

        // 게시물 좋아요 수 변동
        postMeta.setHeartCount(postDAO.getHeartCount());

        // 게시물 반환
        return postMeta;
    }
}
