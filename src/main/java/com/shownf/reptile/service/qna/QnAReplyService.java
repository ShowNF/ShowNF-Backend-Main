package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.bean.SaveQnAReplyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAReplyService {

    SaveQnAReplyBean saveQnAReplyBean;

    @Autowired
    public QnAReplyService(SaveQnAReplyBean saveQnAReplyBean) {
        this.saveQnAReplyBean = saveQnAReplyBean;
    }

    // QnA 대댓글 저장
    public Long saveQnAReply(RequestQnAReplySaveDTO requestQnAReplySaveDTO){
        return saveQnAReplyBean.exec(requestQnAReplySaveDTO);
    }
}
