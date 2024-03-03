package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateQnACommentHeartDAOBean {

    // QnA 댓글 좋아요 저장시 DAO 생성
    public QnACommentHeartDAO exec(Long qnaCommentHeartId, RequestQnACommentHeartSaveDTO requestQnACommentHeartSaveDTO){

        // 댓글 아이디
        Long qnaCommentId = requestQnACommentHeartSaveDTO. getQnaCommentId();

        // 유저 아이디
        Long userId = requestQnACommentHeartSaveDTO.getUserId();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // DAO 반환
        return new QnACommentHeartDAO(qnaCommentHeartId, qnaCommentId, userId, uploadTime);
    }
}
