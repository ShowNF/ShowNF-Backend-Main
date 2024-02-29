package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckDeleteQnAReplyDAOBean {

    // QnA 대댓글 삭제 여부 확인
    public List<QnAReplyDAO> exec(List<QnAReplyDAO> qnAReplyDAOS){

        List<QnAReplyDAO> newQnAReplyDAOs = new ArrayList<>();

        // 삭제 확인
        for (QnAReplyDAO qnAReplyDAO : qnAReplyDAOS){
            if (qnAReplyDAO.isDeleteCheck())
                continue;
            newQnAReplyDAOs.add(qnAReplyDAO);
        }

        return newQnAReplyDAOs;
    }
}
