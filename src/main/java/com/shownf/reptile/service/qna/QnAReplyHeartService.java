package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.bean.DeleteQnAReplyHeartBean;
import com.shownf.reptile.bean.SaveQnAReplyHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class QnAReplyHeartService {

    SaveQnAReplyHeartBean saveQnAReplyHeartBean;
    DeleteQnAReplyHeartBean deleteQnAReplyHeartBean;

    @Autowired
    public QnAReplyHeartService(SaveQnAReplyHeartBean saveQnAReplyHeartBean, DeleteQnAReplyHeartBean deleteQnAReplyHeartBean) {
        this.saveQnAReplyHeartBean = saveQnAReplyHeartBean;
        this.deleteQnAReplyHeartBean = deleteQnAReplyHeartBean;
    }

    // QnA 대댓글 좋아요 저장
    public Long saveQnAReplyHeart(RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){
        return saveQnAReplyHeartBean.exec(requestQnAReplyHeartSaveDTO);
    }

    // QnA 대댓글 좋아요 삭제
    public Long deleteQnAReplyHeart(RequestQnAReplyHeartDeleteDTO requestQnAReplyHeartDeleteDTO, HttpServletRequest request){
        return deleteQnAReplyHeartBean.exec(requestQnAReplyHeartDeleteDTO, request);
    }
}
