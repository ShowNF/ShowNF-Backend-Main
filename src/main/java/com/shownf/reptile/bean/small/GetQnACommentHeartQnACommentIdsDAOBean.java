package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetQnACommentHeartQnACommentIdsDAOBean {

    // 좋아요 눌린 QnA 댓글 아이디 가져오기
    public List<Long> exec(List<QnACommentHeartDAO> qnACommentHeartDAOS){

        List<Long> qnaCommentIds = new ArrayList<>();

        for (QnACommentHeartDAO qnACommentHeartDAO : qnACommentHeartDAOS)
            qnaCommentIds.add(qnACommentHeartDAO.getQnaCommentId());

        return qnaCommentIds;
    }

}
