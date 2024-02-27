package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateQnAPostHeartDAOBean {

    // QnA 게시물 좋아요 저장시 DAO 생성
    public QnAPostHeartDAO exec(Long qnaPostHeartId, RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){

        // QnA 게시물 아이디
        Long qnaPostId = requestQnAPostHeartSaveDTO.getQnaPostId();

        // 유저 아이디
        Long userId = requestQnAPostHeartSaveDTO.getUserId();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // DAO 반환
        return new QnAPostHeartDAO(qnaPostHeartId, qnaPostId, userId, uploadTime);
    }
}
