package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFollowDAOBean {

    FollowRepositoryJPA followRepositoryJPA;

    @Autowired
    public GetFollowDAOBean(FollowRepositoryJPA followRepositoryJPA) {
        this.followRepositoryJPA = followRepositoryJPA;
    }

    public FollowDAO exec(RequestFollowDTO requestFollowDTO){
        return followRepositoryJPA.findByUserIdAndFollowUserId(requestFollowDTO.getUserId(), requestFollowDTO.getFollowUserId());
    }
}
