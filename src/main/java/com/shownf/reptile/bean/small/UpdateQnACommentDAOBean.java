package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateQnACommentDAOBean {

    // QnA 댓글 수정
    public void exec(QnACommentDAO qnaCommentDAO, RequestQnACommentUpdateDTO requestQnACommentUpdateDTO){

        // 내용
        qnaCommentDAO.setContent(requestQnACommentUpdateDTO.getContent());

        // 이미지
        qnaCommentDAO.setImageUrl(requestQnACommentUpdateDTO.getImageUrl());

        // 수정시간
        qnaCommentDAO.setUpdateTime(LocalDateTime.now());
    }

    // QnA 댓글 대댓글 수 수정
    public void exec(QnACommentDAO qnaCommentDAO, RequestQnAReplySaveDTO requestQnAReplySaveDTO){

        // 댓글 대댓글 수 1증가
        qnaCommentDAO.setReplyCount(qnaCommentDAO.getReplyCount() + 1);
    }
}
