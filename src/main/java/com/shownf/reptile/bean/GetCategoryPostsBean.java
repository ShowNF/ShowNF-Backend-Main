package com.shownf.reptile.bean;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.CreatePostsDTOBean;
import com.shownf.reptile.bean.small.GetPostsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class GetCategoryPostsBean {

    GetPostsDAOBean getPostsDAOBean;
    CreatePostsDTOBean createPostsDTOBean;

    @Autowired
    public GetCategoryPostsBean(GetPostsDAOBean getPostsDAOBean, CreatePostsDTOBean createPostsDTOBean) {
        this.getPostsDAOBean = getPostsDAOBean;
        this.createPostsDTOBean = createPostsDTOBean;
    }

    // 카테고리별로 게시물 전체 조회
    public Page<Long> exec(String category, Pageable pageable){

        // 카테고리 찾기
        Page<PostMeta> postMetas = getPostsDAOBean.exec(category, pageable);

        // DAO 객체 DTO 반환
        return createPostsDTOBean.exec(pageable, postMetas);
    }
}
