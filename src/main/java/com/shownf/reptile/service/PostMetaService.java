package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.bean.GetPostMetaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostMetaService {

    GetPostMetaBean getPostMetaBean;

    @Autowired
    public PostMetaService(GetPostMetaBean getPostMetaBean) {
        this.getPostMetaBean = getPostMetaBean;
    }

    // Get the post meta
    public ResponsePostMetaDTO getPostMeta(Long postId){
        return getPostMetaBean.exec(postId);
    }
}
