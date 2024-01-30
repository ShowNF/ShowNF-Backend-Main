package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePostViewCountDAOBean {

    GetPostDAOBean getPostDAOBean;
    GetPostMetaDAOBean getPostMetaDAOBean;

    @Autowired
    public UpdatePostViewCountDAOBean(GetPostDAOBean getPostDAOBean, GetPostMetaDAOBean getPostMetaDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.getPostMetaDAOBean = getPostMetaDAOBean;
    }

    // 게시물 찾기에 대한 조회수 1 증가
    public PostDAO exec(PostDAO postDAO){

        // postId 가져오기
        Long postId = postDAO.getPostId();

        // postId 로 게시물 찾기
        PostDAO postDAO1 = getPostDAOBean.exec(postId);
        if (postDAO1 == null) return null;

        // 게시물 조회수 1 증가
        postDAO1.setViewCount(postDAO.getViewCount() + 1);

        // 게시물 반환
        return postDAO1;
    }

    // 메타데이터 조회수 1 증가
    public PostMeta exec(PostMeta postMeta){

        // postId 가져오기
        Long postId = postMeta.getPostId();

        // postId 로 게시물 찾기
        PostMeta updatePostMeta = getPostMetaDAOBean.exec(postId);
        if (updatePostMeta == null) return null;

        // 게시물 조회수 1 증가
        updatePostMeta.setViewCount(postMeta.getViewCount() + 1);

        // 게시물 반환
        return postMeta;
    }
}
