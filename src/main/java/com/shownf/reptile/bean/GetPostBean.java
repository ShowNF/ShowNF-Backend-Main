package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPostBean {
    GetPostDAOBean getPostDAOBean;
    GetPostMetaDAOBean getPostMetaDAOBean;
    UpdatePostViewCountDAOBean updatePostViewCountDAOBean;
    CreatePostDTOBean createPostDTOBean;
    SavePostDAOBean savePostDAOBean;
    SavePostMetaDAOBean savePostMetaDAOBean;
    SavePostLogDAOBean savePostLogDAOBean;

    @Autowired
    public GetPostBean(GetPostDAOBean getPostDAOBean, GetPostMetaDAOBean getPostMetaDAOBean, UpdatePostViewCountDAOBean updatePostViewCountDAOBean, CreatePostDTOBean createPostDTOBean, SavePostDAOBean savePostDAOBean, SavePostMetaDAOBean savePostMetaDAOBean, SavePostLogDAOBean savePostLogDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.updatePostViewCountDAOBean = updatePostViewCountDAOBean;
        this.createPostDTOBean = createPostDTOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.savePostMetaDAOBean = savePostMetaDAOBean;
        this.savePostLogDAOBean = savePostLogDAOBean;
    }

    public RequestPostDTO exec(Long postId, Long userId){

        // postId 로 게시물 찾기
        PostDAO postDAO = getPostDAOBean.exec(postId);
        if (postDAO == null) return null;

        // 게시물 찾기로 인한 조회수 1 증가
        PostDAO updatePostDAO = updatePostViewCountDAOBean.exec(postDAO);
        if (updatePostDAO == null) return null;

        // DTO 에 게시물 객체 넘기기
        RequestPostDTO requestPostDTO = createPostDTOBean.exec(updatePostDAO);

        // postMeta 게시물 찾기
        PostMeta postMeta = getPostMetaDAOBean.exec(postId);
        if (postMeta == null) return null;

        // 게시물 메타데이터 조회수 증가
        PostMeta updatePostMeta = updatePostViewCountDAOBean.exec(postMeta);

        // 게시물 저장
        savePostDAOBean.exec(updatePostDAO);

        // 게시물 메타데이터 저장
        savePostMetaDAOBean.exec(updatePostMeta);

        // 최근 조회 게시물 추가
        savePostLogDAOBean.exec(postId, userId, 0);

        // DTO 반환
        return requestPostDTO;
    }
}
