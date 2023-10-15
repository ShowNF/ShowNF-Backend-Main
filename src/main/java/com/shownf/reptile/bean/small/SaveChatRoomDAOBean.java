package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.Model.entity.ChatRoomDAO;
import com.shownf.reptile.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveChatRoomDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public SaveChatRoomDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA) {
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    // 채팅방 저장하기
    public void exec(ChatRoomDAO chatRoomDAO){
        chatRoomRepositoryJPA.save(chatRoomDAO);
    }

    public void exec(Long uniqueId, RequestChatRoomSaveDTO requestChatRoomSaveDTO){

        // 채팅방 아이디
        String chatRoomId = requestChatRoomSaveDTO.getChatRoomId();

        // 파는사람 아이디
        Long sellerId = requestChatRoomSaveDTO.getSellerId();

        // 사는사람 아이디
        Long buyerId = requestChatRoomSaveDTO.getBuyerId();

        // 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        exec(new ChatRoomDAO(uniqueId, chatRoomId, sellerId, buyerId, uploadTime));
    }
}
