package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnACommentHeartRepositoryJPA extends JpaRepository<QnACommentHeartDAO, Long> {

    QnACommentHeartDAO findByQnaCommentIdAndUserId(Long qnaCommentId, Long userId);
}
