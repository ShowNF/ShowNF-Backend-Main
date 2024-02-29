package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnACommentGetDTO;
import com.shownf.reptile.bean.DeleteQnACommentBean;
import com.shownf.reptile.bean.GetQnACommentsBean;
import com.shownf.reptile.bean.SaveQnACommentBean;
import com.shownf.reptile.bean.UpdateQnACommentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QnACommentService {

    GetQnACommentsBean getQnACommentsBean;
    SaveQnACommentBean saveQnACommentBean;
    UpdateQnACommentBean updateQnACommentBean;
    DeleteQnACommentBean deleteQnACommentBean;

    @Autowired
    public QnACommentService(GetQnACommentsBean getQnACommentsBean, SaveQnACommentBean saveQnACommentBean, UpdateQnACommentBean updateQnACommentBean, DeleteQnACommentBean deleteQnACommentBean) {
        this.getQnACommentsBean = getQnACommentsBean;
        this.saveQnACommentBean = saveQnACommentBean;
        this.updateQnACommentBean = updateQnACommentBean;
        this.deleteQnACommentBean = deleteQnACommentBean;
    }

    // QnA Comment 전체 조회
    public List<ResponseQnACommentGetDTO> getQnAComments(Long qnaPostId){
        return getQnACommentsBean.exec(qnaPostId);
    }

    // QnA Comment 저장
    public Long saveQnAComment(RequestQnACommentSaveDTO requestQnACommentSaveDTO){
        return saveQnACommentBean.exec(requestQnACommentSaveDTO);
    }

    // QnA Comment 수정
    public Long updateQnAComment(RequestQnACommentUpdateDTO requestQnACommentUpdateDTO, HttpServletRequest request){
        return updateQnACommentBean.exec(requestQnACommentUpdateDTO, request);
    }

    // QnA Comment 삭제
    public Long deleteQnAComment(RequestQnACommentDeleteDTO requestQnACommentDeleteDTO, HttpServletRequest request){
        return deleteQnACommentBean.exec(requestQnACommentDeleteDTO, request);
    }
}
