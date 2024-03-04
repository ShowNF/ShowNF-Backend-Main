package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetQnAReplyHeartQnAReplyIdsDAOBean {

    // 좋아요 눌린 QnA 대댓글 아이디 가져오기
    public List<Long> exec(List<QnAReplyHeartDAO> qnAReplyHeartDAOS){

        List<Long> qnaReplyIds = new ArrayList<>();

        for (QnAReplyHeartDAO qnAReplyHeartDAO : qnAReplyHeartDAOS)
            qnaReplyIds.add(qnAReplyHeartDAO.getQnaReplyId());

        return qnaReplyIds;
    }
}
