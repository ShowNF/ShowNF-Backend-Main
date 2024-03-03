package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyUpdateDTO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateQnAReplyDAOBean {

    // QnA 대댓글 수정
    public void exec(QnAReplyDAO qnAReplyDAO, RequestQnAReplyUpdateDTO requestQnAReplyUpdateDTO){

        // 내용
        qnAReplyDAO.setContent(requestQnAReplyUpdateDTO.getContent());

        // 수정시간
        qnAReplyDAO.setUpdateTime(LocalDateTime.now());
    }

    // QnA 대댓글 좋아요 추가에 따른 좋아요 갯수 추가
    public void exec(QnAReplyDAO qnAReplyDAO, RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){
        qnAReplyDAO.setHeartCount(qnAReplyDAO.getHeartCount() + 1);
    }

    // QnA 대댓글 좋아요 삭제에 따른 좋아요 갯수 감소
    public void exec(QnAReplyDAO qnAReplyDAO, RequestQnAReplyHeartDeleteDTO requestQnAReplyHeartDeleteDTO){
        qnAReplyDAO.setHeartCount(qnAReplyDAO.getHeartCount() - 1);
    }
}
