package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.repository.qna.QnACommentHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnACommentHeartDAOBean {

    QnACommentHeartRepositoryJPA qnACommentHeartRepositoryJPA;

    @Autowired
    public GetQnACommentHeartDAOBean(QnACommentHeartRepositoryJPA qnACommentHeartRepositoryJPA) {
        this.qnACommentHeartRepositoryJPA = qnACommentHeartRepositoryJPA;
    }

    // 유저 아이디로 QnA 댓글 좋아요 객체 전부 가져오기
    public List<QnACommentHeartDAO> exec(Long userId){
        return qnACommentHeartRepositoryJPA.findByUserId(userId);
    }

    // QnA 댓글 좋아요 객체 가져오기
    public QnACommentHeartDAO exec(Long qnaCommentId, Long userId){
        return qnACommentHeartRepositoryJPA.findByQnaCommentIdAndUserId(qnaCommentId, userId);
    }
}
