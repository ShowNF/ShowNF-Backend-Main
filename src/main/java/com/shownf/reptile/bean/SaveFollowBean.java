package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.GetFollowDAOBean;
import com.shownf.reptile.bean.small.SaveFollowDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFollowBean {

    GetFollowDAOBean getFollowDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    SaveFollowDAOBean saveFollowDAOBean;

    @Autowired
    public SaveFollowBean(CreateUniqueIdBean createUniqueIdBean, SaveFollowDAOBean saveFollowDAOBean, GetFollowDAOBean getFollowDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.saveFollowDAOBean = saveFollowDAOBean;
        this.getFollowDAOBean = getFollowDAOBean;
    }

    public Long exec(RequestFollowDTO requestFollowDTO){

        // 팔로우 추가 중복 배제
        FollowDAO followDAO = getFollowDAOBean.exec(requestFollowDTO);
        if (followDAO != null)
            return followDAO.getFollowId();

        // followId 생성
        Long followId = createUniqueIdBean.exec();

        // 팔로우 저장
        saveFollowDAOBean.exec(followId, requestFollowDTO);

        return followId;
    }
}
