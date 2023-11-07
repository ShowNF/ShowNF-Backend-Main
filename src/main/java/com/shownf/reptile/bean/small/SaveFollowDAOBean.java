package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.repository.FollowRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveFollowDAOBean {

    FollowRepositoryJPA followRepositoryJPA;
    UpdateUserFollowCountDAOBean updateUserFollowCountDAOBean;

    @Autowired
    public SaveFollowDAOBean(FollowRepositoryJPA followRepositoryJPA, UpdateUserFollowCountDAOBean updateUserFollowCountDAOBean) {
        this.followRepositoryJPA = followRepositoryJPA;
        this.updateUserFollowCountDAOBean = updateUserFollowCountDAOBean;
    }

    public void exec(FollowDAO followDAO){
      followRepositoryJPA.save(followDAO);
    }

    public void exec(Long followId, RequestFollowDTO requestFollowDTO){

        // 유저 아이디
        Long userId = requestFollowDTO.getUserId();

        // 팔로우 당한 유저 아이디
        Long followUserId = requestFollowDTO.getFollowUserId();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        FollowDAO followDAO = new FollowDAO(followId, userId, followUserId, uploadTime);

        // 팔로우 저장
        exec(followDAO);

        // 팔로우 수 팔로잉 수 증가
        updateUserFollowCountDAOBean.exec(followDAO);
    }
}
