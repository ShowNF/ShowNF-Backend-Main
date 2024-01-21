package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // Get post metas from userId
    public Page<PostMeta> exec(Long userId, Pageable pageable){
        return postMetaRepositoryJPA.findByUserId(userId, pageable);
    }
}
