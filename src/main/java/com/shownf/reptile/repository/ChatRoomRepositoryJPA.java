package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.ChatRoomDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepositoryJPA extends JpaRepository<ChatRoomDAO, Long> {
}
