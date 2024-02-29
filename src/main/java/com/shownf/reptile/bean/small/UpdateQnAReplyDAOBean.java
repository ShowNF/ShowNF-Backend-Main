package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyUpdateDTO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateQnAReplyDAOBean {

    public void exec(QnAReplyDAO qnAReplyDAO, RequestQnAReplyUpdateDTO requestQnAReplyUpdateDTO){

        // 내용
        qnAReplyDAO.setContent(requestQnAReplyUpdateDTO.getContent());

        // 수정시간
        qnAReplyDAO.setUpdateTime(LocalDateTime.now());
    }
}
