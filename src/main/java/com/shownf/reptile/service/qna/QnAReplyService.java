package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAReplyGetDTO;
import com.shownf.reptile.bean.DeleteQnAReplyBean;
import com.shownf.reptile.bean.GetQnAReplysBean;
import com.shownf.reptile.bean.SaveQnAReplyBean;
import com.shownf.reptile.bean.UpdateQnAReplyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QnAReplyService {

    GetQnAReplysBean getQnAReplysBean;
    SaveQnAReplyBean saveQnAReplyBean;
    UpdateQnAReplyBean updateQnAReplyBean;
    DeleteQnAReplyBean deleteQnAReplyBean;

    @Autowired
    public QnAReplyService(GetQnAReplysBean getQnAReplysBean, SaveQnAReplyBean saveQnAReplyBean, UpdateQnAReplyBean updateQnAReplyBean, DeleteQnAReplyBean deleteQnAReplyBean) {
        this.getQnAReplysBean = getQnAReplysBean;
        this.saveQnAReplyBean = saveQnAReplyBean;
        this.updateQnAReplyBean = updateQnAReplyBean;
        this.deleteQnAReplyBean = deleteQnAReplyBean;
    }

    // QnA 대댓글 전체 조회
    public List<ResponseQnAReplyGetDTO> getQnAReplys(Long qnaCommentId){
        return getQnAReplysBean.exec(qnaCommentId);
    }

    // QnA 대댓글 저장
    public Long saveQnAReply(RequestQnAReplySaveDTO requestQnAReplySaveDTO){
        return saveQnAReplyBean.exec(requestQnAReplySaveDTO);
    }

    // QnA 대댓글 수정
    public Long updateQnAReply(RequestQnAReplyUpdateDTO requestQnAReplyUpdateDTO, HttpServletRequest request){
        return updateQnAReplyBean.exec(requestQnAReplyUpdateDTO, request);
    }

    // QnA 대댓글 삭제
    public Long deleteQnAReply(RequestQnAReplyDeleteDTO requestQnAReplyDeleteDTO, HttpServletRequest request){
        return deleteQnAReplyBean.exec(requestQnAReplyDeleteDTO, request);
    }
}
