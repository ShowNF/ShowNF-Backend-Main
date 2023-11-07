package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseFollowerDTO;
import com.shownf.reptile.Model.DTO.ResponseFollowingDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.bean.small.CreateFollowsDTOBean;
import com.shownf.reptile.bean.small.GetFollowsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetFollowingsBean {

    GetFollowsDAOBean getFollowsDAOBean;
    CreateFollowsDTOBean createFollowsDTOBean;

    @Autowired
    public GetFollowingsBean(GetFollowsDAOBean getFollowsDAOBean, CreateFollowsDTOBean createFollowsDTOBean) {
        this.getFollowsDAOBean = getFollowsDAOBean;
        this.createFollowsDTOBean = createFollowsDTOBean;
    }

    // 유저 팔로잉 전체 조회
    public List<ResponseFollowingDTO> exec(Long userId){

        // 유저 아이디로 팔로잉 전체 찾기
        List<FollowDAO> followDAOs = getFollowsDAOBean.exec(userId, null);

        // 팔로우 DTO 변환 후 반환
        return createFollowsDTOBean.exec(followDAOs, null);
    }
}
