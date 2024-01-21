package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostDeleteDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class DeletePostBean {

    GetPostDAOBean getPostDAOBean;
    GetPostContentDAOsBean getPostContentDAOsBean;
    GetPostMetaDAOBean getPostMetaDAOBean;
    UpdatePostContentDeleteCheckDAOBean updatePostContentDeleteCheckDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateUserPostCountDAOBean updateUserPostCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SavePostDAOBean savePostDAOBean;
    SavePostContentsDAOBean savePostContentsDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    SavePostMetaDAOBean savePostMetaDAOBean;

    @Autowired
    public DeletePostBean(GetPostDAOBean getPostDAOBean, GetPostContentDAOsBean getPostContentDAOsBean, GetPostMetaDAOBean getPostMetaDAOBean, UpdatePostContentDeleteCheckDAOBean updatePostContentDeleteCheckDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateUserPostCountDAOBean updateUserPostCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SavePostDAOBean savePostDAOBean, SavePostContentsDAOBean savePostContentsDAOBean, SaveUserDAOBean saveUserDAOBean, SavePostMetaDAOBean savePostMetaDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.getPostContentDAOsBean = getPostContentDAOsBean;
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.updatePostContentDeleteCheckDAOBean = updatePostContentDeleteCheckDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateUserPostCountDAOBean = updateUserPostCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.savePostContentsDAOBean = savePostContentsDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.savePostMetaDAOBean = savePostMetaDAOBean;
    }

    // Delete the post
    public Long exec(RequestPostDeleteDTO requestPostDeleteDTO, HttpServletRequest request){

        // 삭제할 게시글 찾기
        PostDAO postDAO = getPostDAOBean.exec(requestPostDeleteDTO.getPostId());
        if (postDAO == null) return 0L;
        System.out.println("Dd");

        // 삭제할 게시글 postContent 찾기
        List<PostContentDAO> postContentDAOs = getPostContentDAOsBean.exec(postDAO.getPostId());

        // 유저 게시물 토큰으로 확인
        if (!checkUserAccessTokenDAOBean.exec(postDAO, request))
            return null;

        // 게시물 deleteCheck 값 true 변경
        postDAO.setDeleteCheck(true);

        // 게시물 postContent deleteCheck 값 true 변경
        List<PostContentDAO> updatePostContentDAOs = updatePostContentDeleteCheckDAOBean.exec(postContentDAOs);

        // 게시물 삭제시 유저 게시물, 좋아요, 댓글 수 감소
        UserDAO userDAO = updateUserPostCountDAOBean.exec(requestPostDeleteDTO, postDAO);
        if (userDAO == null) return 0L;
        System.out.println("Dd");

        // 경험치 삭제
        userDAO = updateUserExpDAOBean.exec(requestPostDeleteDTO, userDAO);

        // 게시물 메타데이터 찾기
        PostMeta postMeta = getPostMetaDAOBean.exec(requestPostDeleteDTO.getPostId());
        if (postMeta == null) return 0L;
        System.out.println("Dd");

        // 게시물 메타데이터 deleteCheck 값 변경
        postMeta.setDeleteCheck(true);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 게시물 postContent 저장
        savePostContentsDAOBean.exec(updatePostContentDAOs);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // 게시물 메타데이터 저장
        savePostMetaDAOBean.exec(postMeta);

        // postId 반환
        return requestPostDeleteDTO.getPostId();
    }
}
