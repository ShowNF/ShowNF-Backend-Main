package com.shownf.reptile.repository;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMetaRepositoryJPA extends JpaRepository<PostMeta, Long> {
    Page<PostMeta> findByUserId(Long userId, Pageable pageable);
}
