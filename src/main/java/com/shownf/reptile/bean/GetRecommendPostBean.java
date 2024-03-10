package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseRecommendPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.CreateRecommendPostDTOBean;
import com.shownf.reptile.bean.small.GetPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRecommendPostBean {

    GetPostMetaDAOBean getPostMetaDAOBean;
    CreateRecommendPostDTOBean createRecommendPostDTOBean;

    @Autowired
    public GetRecommendPostBean(GetPostMetaDAOBean getPostMetaDAOBean, CreateRecommendPostDTOBean createRecommendPostDTOBean) {
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.createRecommendPostDTOBean = createRecommendPostDTOBean;
    }

    // 추천 게시물 가져오기
    public List<ResponseRecommendPostGetDTO> exec(){

        // 추천 게시물 4개 가져오기
        List<PostMeta> postMetas = getPostMetaDAOBean.exec();

        return createRecommendPostDTOBean.exec(postMetas);
    }
}
