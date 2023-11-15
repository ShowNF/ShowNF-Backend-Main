package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCheckReplyDAOBean {

    // 대댓글 삭제 여부 확인
    public List<ReplyDAO> exec(List<ReplyDAO> replyDAOs){

        List<ReplyDAO> newReplyDAOs = new ArrayList<>();

        // 삭제 확인
        for (ReplyDAO replyDAO : replyDAOs){
            if (replyDAO.isDeleteCheck())
                continue;
            newReplyDAOs.add(replyDAO);
        }

        return newReplyDAOs;
    }
}
