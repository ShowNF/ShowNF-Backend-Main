package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.bean.small.CreateQnAPostMetaDTOBean;
import com.shownf.reptile.bean.small.GetQnAPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostMetaBean {

    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    CreateQnAPostMetaDTOBean createQnAPostMetaDTOBean;

    @Autowired
    public GetQnAPostMetaBean(GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, CreateQnAPostMetaDTOBean createQnAPostMetaDTOBean) {
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.createQnAPostMetaDTOBean = createQnAPostMetaDTOBean;
    }

    // Get the QnA post meta
    public ResponseQnAPostMetaDTO exec(Long qnaPostId){

        // QnA postMeta 찾기
        QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(qnaPostId);
        if (qnAPostMeta == null) return null;

        // postMeta DTO 변환 및 반환
        return createQnAPostMetaDTOBean.exec(qnAPostMeta);
    }
}
