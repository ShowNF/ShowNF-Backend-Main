package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.DTO.ResponseRecommendPostGetDTO;
import com.shownf.reptile.bean.GetPostMetaBean;
import com.shownf.reptile.bean.GetPostMetasBean;
import com.shownf.reptile.bean.GetPostSearchBean;
import com.shownf.reptile.bean.GetRecommendPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostMetaService {

    GetPostMetaBean getPostMetaBean;
    GetPostMetasBean getPostMetasBean;
    GetRecommendPostBean getRecommendPostBean;
    GetPostSearchBean getPostSearchBean;

    @Autowired
    public PostMetaService(GetPostMetaBean getPostMetaBean, GetPostMetasBean getPostMetasBean, GetRecommendPostBean getRecommendPostBean, GetPostSearchBean getPostSearchBean) {
        this.getPostMetaBean = getPostMetaBean;
        this.getPostMetasBean = getPostMetasBean;
        this.getRecommendPostBean = getRecommendPostBean;
        this.getPostSearchBean = getPostSearchBean;
    }

    // Get the post meta
    public ResponsePostMetaDTO getPostMeta(Long postId){
        return getPostMetaBean.exec(postId);
    }

    // Get post metas
    public List<ResponsePostMetaDTO> getPostMetas(List<Long> postIds){
        return getPostMetasBean.exec(postIds);
    }

    // Get the recommended post
    public List<ResponseRecommendPostGetDTO> getRecommendPost(){
        return getRecommendPostBean.exec();
    }

    // Get the recommended post by search
    public List<ResponseRecommendPostGetDTO> getRecommendPost(String search, String searchType){
        return getPostSearchBean.exec(search, searchType);
    }

}
