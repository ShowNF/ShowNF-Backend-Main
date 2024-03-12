package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseRecommendQnAPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.bean.small.CreateRecommendQnAPostDTOBean;
import com.shownf.reptile.bean.small.GetQnAPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAPostSearchBean {

    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    CreateRecommendQnAPostDTOBean createRecommendQnAPostDTOBean;

    @Autowired
    public GetQnAPostSearchBean(GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, CreateRecommendQnAPostDTOBean createRecommendQnAPostDTOBean) {
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.createRecommendQnAPostDTOBean = createRecommendQnAPostDTOBean;
    }

    // QnA 게시물 검색
    public List<ResponseRecommendQnAPostGetDTO> exec(String search, String searchType){

        // 검색어가 없을경우
        if(search == null) return null;

        // 검색어가 있을경우
        List<QnAPostMeta> qnAPostMetas = getQnAPostMetaDAOBean.exec(search, searchType);
        if (qnAPostMetas == null) return null;

        // DTO 변환 후 반환
        return createRecommendQnAPostDTOBean.exec(qnAPostMetas);
    }
}
