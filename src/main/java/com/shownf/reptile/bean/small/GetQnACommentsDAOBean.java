package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.repository.QnACommentRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnACommentsDAOBean {

    QnACommentRepositoryJPA qnACommentRepositoryJPA;

    @Autowired
    public GetQnACommentsDAOBean(QnACommentRepositoryJPA qnACommentRepositoryJPA) {
        this.qnACommentRepositoryJPA = qnACommentRepositoryJPA;
    }

    // QnA 댓글 전체 조회
    public List<QnACommentDAO> exec(Long qnaPostId){
        return qnACommentRepositoryJPA.findByQnaPostId(qnaPostId);
    }
}
