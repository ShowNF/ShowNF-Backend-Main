package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.AchievementDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepositoryJPA extends JpaRepository<AchievementDAO, Long> {
}
