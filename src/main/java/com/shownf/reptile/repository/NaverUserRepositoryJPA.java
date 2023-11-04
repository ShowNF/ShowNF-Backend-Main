package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.NaverUserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaverUserRepositoryJPA extends JpaRepository<NaverUserDAO, Long> {
    NaverUserDAO findByNaverId(String naverId);
    NaverUserDAO findByAccessToken(String accessToken);
}
