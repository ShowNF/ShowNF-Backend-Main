package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPostBean {
    GetPostDAOBean getPostDAOBean;
    UpdatePostViewCountDAOBean updatePostViewCountDAOBean;
    CreatePostDTOBean createPostDTOBean;
    SavePostDAOBean savePostDAOBean;

    @Autowired
    public GetPostBean(GetPostDAOBean getPostDAOBean, UpdatePostViewCountDAOBean updatePostViewCountDAOBean, CreatePostDTOBean createPostDTOBean, SavePostDAOBean savePostDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.updatePostViewCountDAOBean = updatePostViewCountDAOBean;
        this.createPostDTOBean = createPostDTOBean;
        this.savePostDAOBean = savePostDAOBean;
    }

    public RequestPostDTO exec(long postId){

        // postId 로 게시물 찾기
        PostDAO postDAO = getPostDAOBean.exec(postId);
        if (postDAO == null) return null;

        // 게시물 찾기로 인한 조회수 1 증가
        PostDAO findPostDAO = updatePostViewCountDAOBean.exec(postDAO);
        if (findPostDAO == null) return null;

        // DTO 에 게시물 객체 넘기기
        RequestPostDTO requestPostDTO = createPostDTOBean.exec(findPostDAO);

        // 게시물 저장
        savePostDAOBean.exec(findPostDAO);

        // DTO 반환
        return requestPostDTO;
    }
}
