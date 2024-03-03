package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyHeartSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateQnAReplyHeartDAOBean {

    // QnA 대댓글 좋아요 저장시 DAO 생성
    public QnAReplyHeartDAO exec(Long qnaReplyHeartId, RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){

        // QnA 대댓글 아이디
        Long qnaReplyId = requestQnAReplyHeartSaveDTO.getQnaReplyId();

        // 유저 아이디
        Long userId = requestQnAReplyHeartSaveDTO.getUserId();

        // 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        return new QnAReplyHeartDAO(qnaReplyHeartId, qnaReplyId, userId, uploadTime);
    }
}
