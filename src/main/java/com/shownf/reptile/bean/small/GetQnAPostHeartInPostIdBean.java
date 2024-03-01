package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetQnAPostHeartInPostIdBean {

    // 좋아요 게시물에서 QnA 게시물 아이디 가져오기
    public List<Long> exec(List<QnAPostHeartDAO> qnAPostHeartDAOS){

        List<Long> qnaPostIds = new ArrayList<>();

        for (QnAPostHeartDAO qnAPostHeartDAO : qnAPostHeartDAOS)
            qnaPostIds.add(qnAPostHeartDAO.getQnaPostId());

        return qnaPostIds;
    }
}
