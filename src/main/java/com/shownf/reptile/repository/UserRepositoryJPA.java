package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<UserDAO, Long> {
    UserDAO findByUserId(String userId);
}
