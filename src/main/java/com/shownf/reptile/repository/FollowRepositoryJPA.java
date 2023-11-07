package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.FollowDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepositoryJPA extends JpaRepository<FollowDAO, Long> {
    FollowDAO findByUserIdAndFollowUserId(Long userId, Long followUserId);

    List<FollowDAO> findByUserId(Long userId);

    List<FollowDAO> findByFollowUserId(Long userId);
}
