package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.PostLogDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLogRepositoryJPA extends JpaRepository<PostLogDAO, Long> {

    List<PostLogDAO> findByUserId(Long userId);
}
