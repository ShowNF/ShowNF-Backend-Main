package com.shownf.reptile.repository;

import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepositoryJPA extends JpaRepository<PostDAO, Long> {
    Page<PostDAO> findAll(Pageable pageable);

    Page<PostDAO> findByUserId(Long userId, Pageable pageable);

    Page<PostDAO> findByCategory(Category category, Pageable pageable);

    Page<PostDAO> findAllByPostIdIn(List<Long> postId, Pageable pageable);
}
