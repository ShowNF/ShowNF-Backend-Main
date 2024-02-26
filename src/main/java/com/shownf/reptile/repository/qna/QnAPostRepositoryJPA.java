package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnAPostRepositoryJPA extends JpaRepository<QnAPostDAO, Long> {

    Page<QnAPostDAO> findAll(Pageable pageable);

    Page<QnAPostDAO> findByUserId(Long userId, Pageable pageable);
}
