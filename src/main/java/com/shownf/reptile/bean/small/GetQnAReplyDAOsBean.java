package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.repository.qna.QnAReplyRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAReplyDAOsBean {

    QnAReplyRepositoryJPA qnAReplyRepositoryJPA;

    @Autowired
    public GetQnAReplyDAOsBean(QnAReplyRepositoryJPA qnAReplyRepositoryJPA) {
        this.qnAReplyRepositoryJPA = qnAReplyRepositoryJPA;
    }

    // 댓글에 해당하는 대댓글 전부 조회
    public List<QnAReplyDAO> exec(Long qnaCommentId){
        return qnAReplyRepositoryJPA.findByQnaCommentId(qnaCommentId);
    }
}
