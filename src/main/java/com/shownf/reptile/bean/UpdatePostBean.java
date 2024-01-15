package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostUpdateDTO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UpdatePostBean {

    GetPostDAOBean getPostDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdatePostContentDAOBean updatePostContentDAOBean;
    UpdatePostDAOBean updatePostDAOBean;
    SavePostDAOBean savePostDAOBean;

    @Autowired
    public UpdatePostBean(GetPostDAOBean getPostDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdatePostContentDAOBean updatePostContentDAOBean, UpdatePostDAOBean updatePostDAOBean, SavePostDAOBean savePostDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updatePostContentDAOBean = updatePostContentDAOBean;
        this.updatePostDAOBean = updatePostDAOBean;
        this.savePostDAOBean = savePostDAOBean;
    }

    // Update the post
    public Long exec(RequestPostUpdateDTO requestPostUpdateDTO, HttpServletRequest request){

        // 게시물 찾기
        PostDAO postDAO = getPostDAOBean.exec(requestPostUpdateDTO.getPostId());
        if (postDAO == null) return 0L;

        // 유저 확인
        UserDAO userDAO = getUserDAOBean.exec(requestPostUpdateDTO.getUserId());
        if (userDAO == null) return 0L;

        if (!checkUserAccessTokenDAOBean.exec(userDAO, request))
            return 0L;

        // postContent 수정
        String updateContent = updatePostContentDAOBean.exec(requestPostUpdateDTO, postDAO);

        // 게시물 수정
        PostDAO updatePostDAO = updatePostDAOBean.exec(updateContent, requestPostUpdateDTO, postDAO);

        // 게시물 저장
        savePostDAOBean.exec(updatePostDAO);

        return requestPostUpdateDTO.getPostId();
    }
}
