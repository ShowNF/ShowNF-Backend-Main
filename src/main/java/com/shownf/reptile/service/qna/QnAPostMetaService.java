package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseRecommendQnAPostGetDTO;
import com.shownf.reptile.bean.GetQnAPostMetaBean;
import com.shownf.reptile.bean.GetQnAPostMetasBean;
import com.shownf.reptile.bean.GetQnAPostSearchBean;
import com.shownf.reptile.bean.GetRecommendQnAPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAPostMetaService {

    GetQnAPostMetaBean getQnAPostMetaBean;
    GetQnAPostMetasBean getQnAPostMetasBean;
    GetRecommendQnAPostBean getRecommendQnAPostBean;
    GetQnAPostSearchBean getQnAPostSearchBean;

    @Autowired
    public QnAPostMetaService(GetQnAPostMetaBean getQnAPostMetaBean, GetQnAPostMetasBean getQnAPostMetasBean, GetRecommendQnAPostBean getRecommendQnAPostBean, GetQnAPostSearchBean getQnAPostSearchBean) {
        this.getQnAPostMetaBean = getQnAPostMetaBean;
        this.getQnAPostMetasBean = getQnAPostMetasBean;
        this.getRecommendQnAPostBean = getRecommendQnAPostBean;
        this.getQnAPostSearchBean = getQnAPostSearchBean;
    }

    // QnA post meta 가져오기
    public ResponseQnAPostMetaDTO getQnAPostMeta(Long qnaPostId){
        return getQnAPostMetaBean.exec(qnaPostId);
    }

    // QnA post metas 가져오기
    public List<ResponseQnAPostMetaDTO> getQnAPostMetas(List<Long> qnaPostIds){
        return getQnAPostMetasBean.exec(qnaPostIds);
    }

    // 추천 QnA 게시물 4개 가져오기
    public List<ResponseRecommendQnAPostGetDTO> getRecommendQnAPostMetas(){
        return getRecommendQnAPostBean.exec();
    }

    // QnA 게시물 검색
    public List<ResponseRecommendQnAPostGetDTO> getQnAPostSearch(String search, String searchType){
        return getQnAPostSearchBean.exec(search, searchType);
    }
}
