package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckDeleteQnACommentDAOBean {

    // QnA 댓글 삭제 여부 확인
    public List<QnACommentDAO> exec(List<QnACommentDAO> qnaCommentDAOs){

        List<QnACommentDAO> newCommentDAOs = new ArrayList<>();

        // 삭제 확인
        for (QnACommentDAO qnACommentDAO : qnaCommentDAOs){
            if (qnACommentDAO.isDeleteCheck()) {
                if (qnACommentDAO.getReplyCount() < 1) continue;
                qnACommentDAO.setContent("삭제된 댓글입니다");
                qnACommentDAO.setUserId(0L);
                qnACommentDAO.setHeartCount(0);
                qnACommentDAO.setReplyCount(0);
            }
            newCommentDAOs.add(qnACommentDAO);
        }

        return newCommentDAOs;
    }
}
