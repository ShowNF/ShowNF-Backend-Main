package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFollowsDAOBean {

    FollowRepositoryJPA followRepositoryJPA;

    @Autowired
    public GetFollowsDAOBean(FollowRepositoryJPA followRepositoryJPA) {
        this.followRepositoryJPA = followRepositoryJPA;
    }

    // 유저아이디로 팔로우 전체 찾기
    public List<FollowDAO> exec(Long userId){
        return followRepositoryJPA.findByUserId(userId);
    }
}
