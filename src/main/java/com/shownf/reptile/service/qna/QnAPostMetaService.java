package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.bean.GetQnAPostMetaBean;
import com.shownf.reptile.bean.GetQnAPostMetasBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAPostMetaService {

    GetQnAPostMetaBean getQnAPostMetaBean;
    GetQnAPostMetasBean getQnAPostMetasBean;

    @Autowired
    public QnAPostMetaService(GetQnAPostMetaBean getQnAPostMetaBean, GetQnAPostMetasBean getQnAPostMetasBean) {
        this.getQnAPostMetaBean = getQnAPostMetaBean;
        this.getQnAPostMetasBean = getQnAPostMetasBean;
    }

    // QnA post meta 가져오기
    public ResponseQnAPostMetaDTO getQnAPostMeta(Long qnaPostId){
        return getQnAPostMetaBean.exec(qnaPostId);
    }

    // QnA post metas 가져오기
    public List<ResponseQnAPostMetaDTO> getQnAPostMetas(List<Long> qnaPostIds){
        return getQnAPostMetasBean.exec(qnaPostIds);
    }
}
