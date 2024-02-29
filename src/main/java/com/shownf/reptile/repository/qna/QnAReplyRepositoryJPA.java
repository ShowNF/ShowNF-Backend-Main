package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnAReplyRepositoryJPA extends JpaRepository<QnAReplyDAO, Long> {
    List<QnAReplyDAO> findByQnaCommentId(Long qnaCommentId);
}
