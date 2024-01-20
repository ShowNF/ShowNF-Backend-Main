package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.CreatePostMetaDTOBean;
import com.shownf.reptile.bean.small.GetPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostMetasBean {

    GetPostMetaDAOBean getPostMetaDAOBean;
    CreatePostMetaDTOBean createPostMetaDTOBean;

    @Autowired
    public GetPostMetasBean(GetPostMetaDAOBean getPostMetaDAOBean, CreatePostMetaDTOBean createPostMetaDTOBean) {
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.createPostMetaDTOBean = createPostMetaDTOBean;
    }

    // Get post metas
    public List<ResponsePostMetaDTO> exec(List<Long> postIds){

        // 메타데이터 가져오기
        List<PostMeta> postMetas = getPostMetaDAOBean.exec(postIds);

        // 메타데이터 DTO 변환 및 반환
        return createPostMetaDTOBean.exec(postMetas);
    }
}
