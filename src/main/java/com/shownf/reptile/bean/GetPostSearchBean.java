package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseRecommendPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.CreateRecommendPostDTOBean;
import com.shownf.reptile.bean.small.GetPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostSearchBean {

    GetPostMetaDAOBean getPostMetaDAOBean;
    CreateRecommendPostDTOBean createRecommendPostDTOBean;

    @Autowired
    public GetPostSearchBean(GetPostMetaDAOBean getPostMetaDAOBean, CreateRecommendPostDTOBean createRecommendPostDTOBean) {
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.createRecommendPostDTOBean = createRecommendPostDTOBean;
    }

    // 게시물 검색 조회
    public List<ResponseRecommendPostGetDTO> exec(String search, String searchType) {

        if (search == null) return null;

        List<PostMeta> postMetas = null;
        if (searchType.equals("hot")){
            // 검색어를 기준으로 게시물을 조회
            postMetas = getPostMetaDAOBean.exec(search);
        }
        else if (searchType.equals("new")){
            // 검색어를 기준으로 게시물을 조회
            postMetas = getPostMetaDAOBean.exec(search, searchType);
        }
        else return null;

        // DTO 변환 후 반환
        return createRecommendPostDTOBean.exec(postMetas);
    }
}
