package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostHeartRepositoryJPA extends JpaRepository<PostHeartDAO, Long> {
    List<PostHeartDAO> findByUserId(Long userId);

    PostHeartDAO findByUserIdAndPostId(Long userId, Long postId);
}
