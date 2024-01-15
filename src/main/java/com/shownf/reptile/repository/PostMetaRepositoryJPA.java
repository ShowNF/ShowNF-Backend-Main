package com.shownf.reptile.repository;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMetaRepositoryJPA extends JpaRepository<PostMeta, Long> {
}
