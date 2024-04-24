package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.Model.Enum.ChatCategory;
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

        // 카테고리
        ChatCategory chatCategory = ChatCategory.valueOf(requestChatRoomSaveDTO.getCategory());

        // 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 채팅 내용
        String content = null;

        // 채팅 보낸 사람 아이디
        Long contentUserId = null;

        // 채팅 보낸 시간
        LocalDateTime contentUploadTime = null;

        exec(new ChatRoomDAO(uniqueId, chatRoomId, sellerId, buyerId, chatCategory, content, contentUserId, contentUploadTime, uploadTime));
    }
}
