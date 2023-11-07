package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.bean.small.CreateFollowsDTOBean;
import com.shownf.reptile.bean.small.GetFollowsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFollowersBean {

    GetFollowsDAOBean getFollowsDAOBean;
    CreateFollowsDTOBean createFollowsDTOBean;

    @Autowired
    public GetFollowersBean(GetFollowsDAOBean getFollowsDAOBean, CreateFollowsDTOBean createFollowsDTOBean) {
        this.getFollowsDAOBean = getFollowsDAOBean;
        this.createFollowsDTOBean = createFollowsDTOBean;
    }

    // 유저 팔로워 전체 조회
    public List<ResponseFollowDTO> exec(Long userId){

        // 유저 아이디로 팔로우 전체 찾기
        List<FollowDAO> followDAOs = getFollowsDAOBean.exec(userId);

        // 팔로우 DTO 변환 후 반환
        return createFollowsDTOBean.exec(followDAOs);
    }
}
