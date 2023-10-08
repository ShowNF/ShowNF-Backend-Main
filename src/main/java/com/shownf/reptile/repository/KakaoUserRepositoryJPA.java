package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.KakaoUserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoUserRepositoryJPA extends JpaRepository<KakaoUserDAO, Long> {
    KakaoUserDAO findByKakaoId(String kakaoId);
}
