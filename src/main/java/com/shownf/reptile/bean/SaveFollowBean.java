package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.SaveFollowDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveFollowBean {

    CreateUniqueIdBean createUniqueIdBean;
    SaveFollowDAOBean saveFollowDAOBean;

    @Autowired
    public SaveFollowBean(CreateUniqueIdBean createUniqueIdBean, SaveFollowDAOBean saveFollowDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.saveFollowDAOBean = saveFollowDAOBean;
    }

    public Long exec(RequestFollowDTO requestFollowDTO){

        // followId 생성
        Long followId = createUniqueIdBean.exec();

        // 팔로우 저장
        saveFollowDAOBean.exec(followId, requestFollowDTO);

        return followId;
    }
}
