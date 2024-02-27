package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnAPostHeartRepositoryJPA extends JpaRepository<QnAPostHeartDAO, Long> {
    QnAPostHeartDAO findByUserIdAndQnaPostId(Long userId, Long qnAPostId);
}
