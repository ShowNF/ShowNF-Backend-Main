package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteFollowDAOBean {

    FollowRepositoryJPA followRepositoryJPA;

    @Autowired
    public DeleteFollowDAOBean(FollowRepositoryJPA followRepositoryJPA) {
        this.followRepositoryJPA = followRepositoryJPA;
    }

    public void exec(FollowDAO followDAO){
        followRepositoryJPA.delete(followDAO);
    }
}
