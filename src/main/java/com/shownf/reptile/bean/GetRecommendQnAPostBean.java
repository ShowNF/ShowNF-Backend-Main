package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseRecommendQnAPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.bean.small.CreateRecommendQnAPostDTOBean;
import com.shownf.reptile.bean.small.GetQnAPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRecommendQnAPostBean {

    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    CreateRecommendQnAPostDTOBean createRecommendQnAPostDTOBean;

    @Autowired
    public GetRecommendQnAPostBean(GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, CreateRecommendQnAPostDTOBean createRecommendQnAPostDTOBean) {
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.createRecommendQnAPostDTOBean = createRecommendQnAPostDTOBean;
    }

    // 추천 QnA 게시물 4개 가져오기
    public List<ResponseRecommendQnAPostGetDTO> exec(){

        // Get the top 4 recommended QnA Posts
        List<QnAPostMeta> qnAPostMetas = getQnAPostMetaDAOBean.exec();

        return createRecommendQnAPostDTOBean.exec(qnAPostMetas);
    }
}
