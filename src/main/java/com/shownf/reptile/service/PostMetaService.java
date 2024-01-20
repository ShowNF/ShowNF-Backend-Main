package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.bean.GetPostMetaBean;
import com.shownf.reptile.bean.GetPostMetasBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostMetaService {

    GetPostMetaBean getPostMetaBean;
    GetPostMetasBean getPostMetasBean;

    @Autowired
    public PostMetaService(GetPostMetaBean getPostMetaBean, GetPostMetasBean getPostMetasBean) {
        this.getPostMetaBean = getPostMetaBean;
        this.getPostMetasBean = getPostMetasBean;
    }

    // Get the post meta
    public ResponsePostMetaDTO getPostMeta(Long postId){
        return getPostMetaBean.exec(postId);
    }

    // Get post metas
    public List<ResponsePostMetaDTO> getPostMetas(List<Long> postIds){
        return getPostMetasBean.exec(postIds);
    }
}
