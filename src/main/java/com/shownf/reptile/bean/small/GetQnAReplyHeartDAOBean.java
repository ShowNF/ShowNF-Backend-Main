package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import com.shownf.reptile.repository.qna.QnAReplyHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAReplyHeartDAOBean {

    QnAReplyHeartRepositoryJPA qnAReplyHeartRepositoryJPA;

    @Autowired
    public GetQnAReplyHeartDAOBean(QnAReplyHeartRepositoryJPA qnAReplyHeartRepositoryJPA) {
        this.qnAReplyHeartRepositoryJPA = qnAReplyHeartRepositoryJPA;
    }

    // 유저 아이디로 QNA 대댓글 좋아요 객체 전부 가져오기
    public List<QnAReplyHeartDAO> exec(Long userId) {
        return qnAReplyHeartRepositoryJPA.findByUserId(userId);
    }

    // QnA 대댓글 좋아요 객체 가져오기
    public QnAReplyHeartDAO exec(Long qnaReplyId, Long userId) {
        return qnAReplyHeartRepositoryJPA.findByQnaReplyIdAndUserId(qnaReplyId, userId);
    }
}
