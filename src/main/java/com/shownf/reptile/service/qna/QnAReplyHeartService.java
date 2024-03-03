package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.bean.SaveQnAReplyHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnAReplyHeartService {

    SaveQnAReplyHeartBean saveQnAReplyHeartBean;

    @Autowired
    public QnAReplyHeartService(SaveQnAReplyHeartBean saveQnAReplyHeartBean) {
        this.saveQnAReplyHeartBean = saveQnAReplyHeartBean;
    }

    // QnA 대댓글 좋아요 저장
    public Long saveQnAReplyHeart(RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){
        return saveQnAReplyHeartBean.exec(requestQnAReplyHeartSaveDTO);
    }
}
