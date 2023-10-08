package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.GoogleUserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleUserRepositoryJPA extends JpaRepository<GoogleUserDAO, Long> {
    GoogleUserDAO findByGoogleId(String googleId);
}
