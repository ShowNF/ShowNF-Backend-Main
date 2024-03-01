package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnAPostHeartRepositoryJPA extends JpaRepository<QnAPostHeartDAO, Long> {

    List<QnAPostHeartDAO> findByUserId(Long userId);
    QnAPostHeartDAO findByUserIdAndQnaPostId(Long userId, Long qnAPostId);
}
