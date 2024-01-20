package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostUpdateDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public class UpdatePostBean {

    GetPostDAOBean getPostDAOBean;
    GetPostMetaDAOBean getPostMetaDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdatePostContentDAOBean updatePostContentDAOBean;
    UpdatePostDAOBean updatePostDAOBean;
    SavePostDAOBean savePostDAOBean;
    SavePostMetaDAOBean savePostMetaDAOBean;

    @Autowired
    public UpdatePostBean(GetPostDAOBean getPostDAOBean, GetPostMetaDAOBean getPostMetaDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdatePostContentDAOBean updatePostContentDAOBean, UpdatePostDAOBean updatePostDAOBean, SavePostDAOBean savePostDAOBean, SavePostMetaDAOBean savePostMetaDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updatePostContentDAOBean = updatePostContentDAOBean;
        this.updatePostDAOBean = updatePostDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.savePostMetaDAOBean = savePostMetaDAOBean;
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
        List<Map<Integer, Long>> updateContent = updatePostContentDAOBean.exec(requestPostUpdateDTO, postDAO);

        // 게시물 수정
        PostDAO updatePostDAO = updatePostDAOBean.exec(updateContent, requestPostUpdateDTO, postDAO);

        // 게시물 메타데이터 찾기
        PostMeta postMeta = getPostMetaDAOBean.exec(requestPostUpdateDTO.getPostId());
        if (postMeta == null) return 0L;

        // 메타데이터 수정
        PostMeta updatePostMeta = updatePostDAOBean.exec(updateContent, requestPostUpdateDTO, postMeta);

        // 게시물 저장
        savePostDAOBean.exec(updatePostDAO);

        // 메타데이터 저장
        savePostMetaDAOBean.exec(updatePostMeta);

        return requestPostUpdateDTO.getPostId();
    }
}
