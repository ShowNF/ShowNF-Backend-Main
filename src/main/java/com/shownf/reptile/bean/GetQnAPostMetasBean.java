package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.bean.small.CreateQnAPostMetaDTOBean;
import com.shownf.reptile.bean.small.GetQnAPostMetaDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAPostMetasBean {

    GetQnAPostMetaDAOBean getPostMetaDAOBean;
    CreateQnAPostMetaDTOBean createQnAPostMetaDTOBean;

    @Autowired
    public GetQnAPostMetasBean(GetQnAPostMetaDAOBean getPostMetaDAOBean, CreateQnAPostMetaDTOBean createQnAPostMetaDTOBean) {
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.createQnAPostMetaDTOBean = createQnAPostMetaDTOBean;
    }

    // Get post metas
    public List<ResponseQnAPostMetaDTO> exec(List<Long> qnaPostIds){

        // 메타데이터 가져오기
        List<QnAPostMeta> qnAPostMetas = getPostMetaDAOBean.exec(qnaPostIds);

        // 메타데이터 DTO 변환 및 반환
        return createQnAPostMetaDTOBean.exec(qnAPostMetas);
    }
}
