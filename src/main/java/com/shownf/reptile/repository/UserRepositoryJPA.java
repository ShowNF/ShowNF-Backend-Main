package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositoryJPA extends JpaRepository<UserDAO, Long> {
    UserDAO findByOauthId(String userId);

    List<UserDAO> findTop5ByOrderByFollowingCountDesc();

    List<UserDAO> findAllByOrderByFollowingCountDesc();
}
