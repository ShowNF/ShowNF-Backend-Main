package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentHeartRepositoryJPA extends JpaRepository<CommentHeartDAO, Long> {

    List<CommentHeartDAO> findByUserId(Long userId);

    CommentHeartDAO findByUserIdAndCommentId(Long userId, Long commentId);
}
