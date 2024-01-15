package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
