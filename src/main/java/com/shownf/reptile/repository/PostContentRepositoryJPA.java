package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.PostContentDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostContentRepositoryJPA extends JpaRepository<PostContentDAO, Long> {
    List<PostContentDAO> findByPostId(Long postId);
}
