package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostMetaDAOBean {

    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @Autowired
    public GetPostMetaDAOBean(PostMetaRepositoryJPA postMetaRepositoryJPA) {
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
    }

    // Get the post meta
    public PostMeta exec(Long postId){
        return postMetaRepositoryJPA.findById(postId).orElse(null);
    }

    // Get post metas
    public List<PostMeta> exec(List<Long> postIds){
        return postMetaRepositoryJPA.findAllById(postIds);
    }

    // 좋아요순으로 추천 게시글 4개 가져오기
    public List<PostMeta> exec(){
        return postMetaRepositoryJPA.findTop4ByOrderByHeartCountDesc();
    }
}
