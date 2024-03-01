package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.bean.GetQnAPostMetaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAPostMetaService {

    GetQnAPostMetaBean getQnAPostMetaBean;

    @Autowired
    public QnAPostMetaService(GetQnAPostMetaBean getQnAPostMetaBean) {
        this.getQnAPostMetaBean = getQnAPostMetaBean;
    }

    // QnA post meta 가져오기
    public ResponseQnAPostMetaDTO getQnAPostMeta(Long qnaPostId){
        return getQnAPostMetaBean.exec(qnaPostId);
    }
}
