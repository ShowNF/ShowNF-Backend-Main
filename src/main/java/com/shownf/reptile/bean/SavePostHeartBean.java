package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostHeartSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePostHeartBean {

    GetPostHeartDAOBean getPostHeartDAOBean;
    CreateUniqueIdBean createUniqueIdBean;
    CreatePostHeartDAOBean createPostHeartDAOBean;
    UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean;
    UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SavePostHeartDAOBean savePostHeartDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SavePostHeartBean(GetPostHeartDAOBean getPostHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreatePostHeartDAOBean createPostHeartDAOBean, UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean, UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SavePostHeartDAOBean savePostHeartDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPostHeartDAOBean = getPostHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createPostHeartDAOBean = createPostHeartDAOBean;
        this.updatePostHeartCountDAOBean = updatePostHeartCountDAOBean;
        this.updateUserHeartCountDAOBean = updateUserHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.savePostHeartDAOBean = savePostHeartDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // 게시물 좋아요 저장
    public Long exec(RequestPostHeartSaveDTO requestPostHeartSaveDTO){

        // 게시물 좋아요 중복 배제
        if (getPostHeartDAOBean.exec(requestPostHeartSaveDTO) != null)
            return 0L;

        // postHeartId 생성
        Long postHeartId = createUniqueIdBean.exec();

        // 게시물 좋아요 생성
        PostHeartDAO postHeartDAO = createPostHeartDAOBean.exec(postHeartId, requestPostHeartSaveDTO);

        // 게시물의 좋아요 갯수 추가
        PostDAO postDAO = updatePostHeartCountDAOBean.exec(postHeartDAO);
        if (postDAO == null) return 0L;

        // 좋아요 receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(postDAO);
        if (userDAO1 == null) return 0L;

        // 좋아요 sender 추가
        UserDAO userDAO2;
        if (requestPostHeartSaveDTO.getUserId().equals(userDAO1.getUserId()))
            userDAO2 = updateUserSendHeartDAOBean.exec(postHeartDAO, userDAO1);
        else userDAO2 = updateUserSendHeartDAOBean.exec(postHeartDAO);
        if (userDAO2 == null) return 0L;

        // 유저 경험치 추가
        userDAO2 = updateUserExpDAOBean.exec(postHeartDAO, userDAO2);

        // 좋아요 저장
        savePostHeartDAOBean.exec(postHeartDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // postHeartId 반환
        return postHeartId;
    }
}
