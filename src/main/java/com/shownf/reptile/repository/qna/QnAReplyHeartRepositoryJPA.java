package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnAReplyHeartRepositoryJPA extends JpaRepository<QnAReplyHeartDAO, Long> {
    List<QnAReplyHeartDAO> findByUserId(Long userId);
    QnAReplyHeartDAO findByQnaReplyIdAndUserId(Long qnaReplyId, Long userId);
}
