package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetReplyHeartReplyIdsDAOBean {


    // 좋아요 누른 대댓글 아이디 가져오기
    public List<Long> exec(List<ReplyHeartDAO> replyHeartDAOS){
        List<Long> replyIds = new ArrayList<>();

        for (ReplyHeartDAO replyHeartDAO : replyHeartDAOS)
            replyIds.add(replyHeartDAO.getReplyId());

        return replyIds;
    }
}
