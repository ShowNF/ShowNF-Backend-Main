package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplySaveDTO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateQnAReplyDAOBean {

    // 대댓글 저장시 DAO 생성
    public QnAReplyDAO exec(Long qnaReplyId, RequestQnAReplySaveDTO requestQnAReplySaveDTO){

        // 댓글 아이디
        Long qnaCommentId = requestQnAReplySaveDTO.getQnaCommentId();

        // 유저 아이디
        Long userId = requestQnAReplySaveDTO.getUserId();

        // 내용
        String content = requestQnAReplySaveDTO.getContent();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 업로드 시간
        LocalDateTime updateTime = LocalDateTime.now();

        // 대댓글 좋아요 갯수
        Integer heartCount = 0;

        // 삭제 여부
        boolean deleteCheck = false;

        // DAO 반환
        return new QnAReplyDAO(qnaReplyId, qnaCommentId, userId, content, uploadTime, updateTime, heartCount, deleteCheck);
    }
}
