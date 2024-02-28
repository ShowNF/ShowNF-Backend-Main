package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnACommentRepositoryJPA extends JpaRepository<QnACommentDAO, Long> {
}
