package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.ChatRoomDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepositoryJPA extends JpaRepository<ChatRoomDAO, Long> {
    List<ChatRoomDAO> findBySellerId(Long userId);
    List<ChatRoomDAO> findByBuyerId(Long userId);
    ChatRoomDAO findByChatRoomId(String chatRoomId);
}
