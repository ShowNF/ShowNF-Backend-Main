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
    SavePostHeartDAOBean savePostHeartDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SavePostHeartBean(GetPostHeartDAOBean getPostHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreatePostHeartDAOBean createPostHeartDAOBean, UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean, UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, SavePostHeartDAOBean savePostHeartDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPostHeartDAOBean = getPostHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createPostHeartDAOBean = createPostHeartDAOBean;
        this.updatePostHeartCountDAOBean = updatePostHeartCountDAOBean;
        this.updateUserHeartCountDAOBean = updateUserHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
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

        // DTO 객체 DAO 변환
        PostHeartDAO postHeartDAO = createPostHeartDAOBean.exec(postHeartId, requestPostHeartSaveDTO);

        // 게시물 좋아요 갯수 추가
        PostDAO postDAO = updatePostHeartCountDAOBean.exec(postHeartDAO);
        if (postDAO == null) return 0L;

        // 유저 좋아요 갯수 추가
        UserDAO userDAO = updateUserHeartCountDAOBean.exec(postDAO);
        if (userDAO == null) return null;

        // 좋아요 sender, receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(postDAO);
        if (userDAO1 == null) return 0L;

        UserDAO userDAO2 = updateUserSendHeartDAOBean.exec(postHeartDAO);
        if (userDAO2 == null) return 0L;

        // 좋아요 저장
        savePostHeartDAOBean.exec(postHeartDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // postHeartId 반환
        return postHeartId;
    }
}
