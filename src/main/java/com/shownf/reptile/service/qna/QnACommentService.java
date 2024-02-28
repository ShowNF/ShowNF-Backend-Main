package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnACommentGetDTO;
import com.shownf.reptile.bean.GetQnACommentsBean;
import com.shownf.reptile.bean.SaveQnACommentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnACommentService {

    GetQnACommentsBean getQnACommentsBean;
    SaveQnACommentBean saveQnACommentBean;

    @Autowired
    public QnACommentService(GetQnACommentsBean getQnACommentsBean, SaveQnACommentBean saveQnACommentBean) {
        this.getQnACommentsBean = getQnACommentsBean;
        this.saveQnACommentBean = saveQnACommentBean;
    }

    // QnA Comment 전체 조회
    public List<ResponseQnACommentGetDTO> getQnAComments(Long qnaPostId){
        return getQnACommentsBean.exec(qnaPostId);
    }

    // QnA Comment 저장
    public Long saveQnAComment(RequestQnACommentSaveDTO requestQnACommentSaveDTO){
        return saveQnACommentBean.exec(requestQnACommentSaveDTO);
    }
}
