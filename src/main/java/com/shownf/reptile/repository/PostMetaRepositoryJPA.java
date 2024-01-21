package com.shownf.reptile.repository;

import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMetaRepositoryJPA extends JpaRepository<PostMeta, Long> {

    Page<PostMeta> findAll(Pageable pageable);
    Page<PostMeta> findByUserId(Long userId, Pageable pageable);

    Page<PostMeta> findByCategory(Category category, Pageable pageable);

    Page<PostMeta> findAllByPostIdIn(List<Long> postId, Pageable pageable);
}
