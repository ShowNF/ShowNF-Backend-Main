package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SavePostBean {

    CreateUniqueIdBean createUniqueIdBean;
    SavePostContentsDAOBean savePostContentsDAOBean;
    SavePostDAOBean savePostDAOBean;
    UpdateUserPostCountDAOBean updateUserPostCountDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SavePostBean(CreateUniqueIdBean createUniqueIdBean, SavePostContentsDAOBean savePostContentsDAOBean, SavePostDAOBean savePostDAOBean, UpdateUserPostCountDAOBean updateUserPostCountDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.savePostContentsDAOBean = savePostContentsDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.updateUserPostCountDAOBean = updateUserPostCountDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // Save the post
    public Long exec(RequestPostSaveDTO requestPostSaveDTO){

        // postId 생성
        Long postId = createUniqueIdBean.exec();

        // postContents 저장
        List<Map<Integer, Long>> postContentIndex = savePostContentsDAOBean.exec(postId, requestPostSaveDTO);

        // 게시물 저장 시 유저 게시물 수 증가
        UserDAO userDAO = updateUserPostCountDAOBean.exec(requestPostSaveDTO);
        if (userDAO == null) return 0L;

        // 게시물 저장
        savePostDAOBean.exec(postId, requestPostSaveDTO, postContentIndex);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // 게시물 postId 반환
        return postId;
    }
}
