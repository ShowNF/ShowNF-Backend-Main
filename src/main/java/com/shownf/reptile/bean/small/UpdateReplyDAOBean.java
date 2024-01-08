package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentUpdateDTO;
import com.shownf.reptile.Model.DTO.RequestReplyUpdateDTO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateReplyDAOBean {

    // Update the reply
    public ReplyDAO exec(ReplyDAO replyDAO, RequestReplyUpdateDTO requestReplyUpdateDTO){

        // 내용
        replyDAO.setContent(requestReplyUpdateDTO.getContent());

        // 수정시간
        replyDAO.setUpdateTime(LocalDateTime.now());

        return replyDAO;
    }
}
