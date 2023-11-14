package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostHeartSaveDTO;
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
    SavePostHeartDAOBean savePostHeartDAOBean;
    UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean;
    SavePostDAOBean savePostDAOBean;
    UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean;

    @Autowired
    public SavePostHeartBean(GetPostHeartDAOBean getPostHeartDAOBean, CreateUniqueIdBean createUniqueIdBean, CreatePostHeartDAOBean createPostHeartDAOBean, SavePostHeartDAOBean savePostHeartDAOBean, UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean, SavePostDAOBean savePostDAOBean, UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean) {
        this.getPostHeartDAOBean = getPostHeartDAOBean;
        this.createUniqueIdBean = createUniqueIdBean;
        this.createPostHeartDAOBean = createPostHeartDAOBean;
        this.savePostHeartDAOBean = savePostHeartDAOBean;
        this.updatePostHeartCountDAOBean = updatePostHeartCountDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.updateUserHeartCountDAOBean = updateUserHeartCountDAOBean;
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

        // 좋아요 저장
        savePostHeartDAOBean.exec(postHeartDAO);

        // 게시물 좋아요 갯수 추가
        PostDAO postDAO = updatePostHeartCountDAOBean.exec(postHeartDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 좋아요 갯수 추가
        updateUserHeartCountDAOBean.exec(requestPostHeartSaveDTO);

        // postHeartId 반환
        return postHeartId;
    }
}
