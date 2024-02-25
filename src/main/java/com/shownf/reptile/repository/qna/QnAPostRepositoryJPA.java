package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnAPostRepositoryJPA extends JpaRepository<QnAPostDAO, Long> {
}
