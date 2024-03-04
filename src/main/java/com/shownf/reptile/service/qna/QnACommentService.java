package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.*;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QnACommentService {

    GetQnACommentsBean getQnACommentsBean;
    SaveQnACommentBean saveQnACommentBean;
    SaveQnACommentSelectionBean saveQnACommentSelectionBean;
    UpdateQnACommentBean updateQnACommentBean;
    DeleteQnACommentBean deleteQnACommentBean;
    DeleteQnACommentSelectionBean deleteQnACommentSelectionBean;

    @Autowired
    public QnACommentService(GetQnACommentsBean getQnACommentsBean, SaveQnACommentBean saveQnACommentBean, SaveQnACommentSelectionBean saveQnACommentSelectionBean, UpdateQnACommentBean updateQnACommentBean, DeleteQnACommentBean deleteQnACommentBean, DeleteQnACommentSelectionBean deleteQnACommentSelectionBean) {
        this.getQnACommentsBean = getQnACommentsBean;
        this.saveQnACommentBean = saveQnACommentBean;
        this.saveQnACommentSelectionBean = saveQnACommentSelectionBean;
        this.updateQnACommentBean = updateQnACommentBean;
        this.deleteQnACommentBean = deleteQnACommentBean;
        this.deleteQnACommentSelectionBean = deleteQnACommentSelectionBean;
    }

    // QnA Comment 전체 조회
    public List<ResponseQnACommentGetDTO> getQnAComments(Long qnaPostId){
        return getQnACommentsBean.exec(qnaPostId);
    }

    // QnA Comment 저장
    public Long saveQnAComment(RequestQnACommentSaveDTO requestQnACommentSaveDTO){
        return saveQnACommentBean.exec(requestQnACommentSaveDTO);
    }

    // QnA Comment 채택
    public ResponseQnACommentGetDTO saveQnACommentSelection(RequestQnACommentSelectionSaveDTO requestQnACommentSelectionSaveDTO, HttpServletRequest request){
        return saveQnACommentSelectionBean.exec(requestQnACommentSelectionSaveDTO, request);
    }

    // QnA Comment 수정
    public Long updateQnAComment(RequestQnACommentUpdateDTO requestQnACommentUpdateDTO, HttpServletRequest request){
        return updateQnACommentBean.exec(requestQnACommentUpdateDTO, request);
    }

    // QnA Comment 삭제
    public Long deleteQnAComment(RequestQnACommentDeleteDTO requestQnACommentDeleteDTO, HttpServletRequest request){
        return deleteQnACommentBean.exec(requestQnACommentDeleteDTO, request);
    }

    // QnA Comment 채택 삭제
    public ResponseQnACommentGetDTO deleteQnACommentSelection(RequestQnACommentSelectionDeleteDTO requestQnACommentSelectionDeleteDTO, HttpServletRequest request){
        return deleteQnACommentSelectionBean.exec(requestQnACommentSelectionDeleteDTO, request);
    }
}
