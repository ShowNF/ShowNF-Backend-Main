package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import com.shownf.reptile.bean.small.DeleteFollowDAOBean;
import com.shownf.reptile.bean.small.GetFollowDAOBean;
import com.shownf.reptile.bean.small.UpdateUserFollowCountDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteFollowBean {

    GetFollowDAOBean getFollowDAOBean;
    DeleteFollowDAOBean deleteFollowDAOBean;
    UpdateUserFollowCountDAOBean updateUserFollowCountDAOBean;

    @Autowired
    public DeleteFollowBean(GetFollowDAOBean getFollowDAOBean, DeleteFollowDAOBean deleteFollowDAOBean, UpdateUserFollowCountDAOBean updateUserFollowCountDAOBean) {
        this.getFollowDAOBean = getFollowDAOBean;
        this.deleteFollowDAOBean = deleteFollowDAOBean;
        this.updateUserFollowCountDAOBean = updateUserFollowCountDAOBean;
    }

    // 팔로우 취소
    public Long exec(RequestFollowDTO requestFollowDTO){

        // 유저와 팔로우 당한 유저로 팔로우 객체 찾기
        FollowDAO followDAO = getFollowDAOBean.exec(requestFollowDTO);

        System.out.println("followDAO = " + followDAO);

        // 팔로우 취소
        deleteFollowDAOBean.exec(followDAO);

        // 팔로우 취소에 따른 유저 팔로우 수, 팔로잉 수 감소
        updateUserFollowCountDAOBean.exec(followDAO.getFollowId(), followDAO);

        return followDAO.getFollowId();
    }
}
