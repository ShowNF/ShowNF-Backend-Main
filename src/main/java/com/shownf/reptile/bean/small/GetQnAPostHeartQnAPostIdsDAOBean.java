package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetQnAPostHeartQnAPostIdsDAOBean {

    // 좋아요 눌린 게시물 아이디 가져오기
    public List<Long> exec(List<QnAPostHeartDAO> qnAostHeartDAOS){

        List<Long> postIds = new ArrayList<>();

        for (QnAPostHeartDAO qnAPostHeartDAO : qnAostHeartDAOS)
            postIds.add(qnAPostHeartDAO.getQnaPostId());

        return postIds;
    }
}
