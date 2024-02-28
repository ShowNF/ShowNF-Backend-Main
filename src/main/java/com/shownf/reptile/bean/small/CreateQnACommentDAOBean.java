package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSaveDTO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateQnACommentDAOBean {

    // QnA 댓글 저장시 DAO 생성
    public QnACommentDAO exec(Long qnaCommentId, RequestQnACommentSaveDTO requestQnACommentSaveDTO){

        // QnA 게시물 아이디
        Long qnaPostId = requestQnACommentSaveDTO.getQnaPostId();

        // 유저 아이디
        Long userId = requestQnACommentSaveDTO.getUserId();

        // 내용
        String content = requestQnACommentSaveDTO.getContent();

        // 이미지 url
        String imageUrl = requestQnACommentSaveDTO.getImageUrl();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 수정 시간
        LocalDateTime updateTime = LocalDateTime.now();

        // 좋아요 갯수
        Integer heartCount = 0;

        // 대댓글 갯수
        Integer replyCount = 0;

        // 채택 여부
        boolean selection = false;

        // 삭제 여부
        boolean deleteCheck = false;

        return new QnACommentDAO(qnaCommentId, qnaPostId, userId, content, imageUrl, uploadTime, updateTime, heartCount, replyCount, selection, deleteCheck);
    }
}
