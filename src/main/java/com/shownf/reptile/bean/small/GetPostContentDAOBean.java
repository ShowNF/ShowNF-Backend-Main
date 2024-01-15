package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.repository.PostContentRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPostContentDAOBean {

    PostContentRepositoryJPA postContentRepositoryJPA;

    @Autowired
    public GetPostContentDAOBean(PostContentRepositoryJPA postContentRepositoryJPA) {
        this.postContentRepositoryJPA = postContentRepositoryJPA;
    }

    // Get the postContent
    public PostContentDAO exec(Long postContentId){
        return postContentRepositoryJPA.findById(postContentId).orElse(null);
    }
}
