package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.CreatePostMetaDTOBean;
import com.shownf.reptile.bean.small.GetPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPostMetaBean {

    GetPostMetaDAOBean getPostMetaDAOBean;
    CreatePostMetaDTOBean createPostMetaDTOBean;

    @Autowired
    public GetPostMetaBean(GetPostMetaDAOBean getPostMetaDAOBean, CreatePostMetaDTOBean createPostMetaDTOBean) {
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.createPostMetaDTOBean = createPostMetaDTOBean;
    }

    // Get the post meta
    public ResponsePostMetaDTO exec(Long postId){

        // postMeta 찾기
        PostMeta postMeta = getPostMetaDAOBean.exec(postId);
        if (postMeta == null) return null;

        // postMeta DTO 변환 및 반환
        return createPostMetaDTOBean.exec(postMeta);
    }
}
