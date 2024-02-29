package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAReplyGetDTO;
import com.shownf.reptile.bean.GetQnAReplysBean;
import com.shownf.reptile.bean.SaveQnAReplyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAReplyService {

    GetQnAReplysBean getQnAReplysBean;
    SaveQnAReplyBean saveQnAReplyBean;

    @Autowired
    public QnAReplyService(SaveQnAReplyBean saveQnAReplyBean, GetQnAReplysBean getQnAReplysBean) {
        this.getQnAReplysBean = getQnAReplysBean;
        this.saveQnAReplyBean = saveQnAReplyBean;
    }

    // QnA 대댓글 전체 조회
    public List<ResponseQnAReplyGetDTO> getQnAReplys(Long qnaCommentId){
        return getQnAReplysBean.exec(qnaCommentId);
    }

    // QnA 대댓글 저장
    public Long saveQnAReply(RequestQnAReplySaveDTO requestQnAReplySaveDTO){
        return saveQnAReplyBean.exec(requestQnAReplySaveDTO);
    }
}
