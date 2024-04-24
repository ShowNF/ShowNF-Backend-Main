package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ChatRoomDAO;
import com.shownf.reptile.repository.ChatRoomRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBuyerChatRoomsDAOBean {

    ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public GetBuyerChatRoomsDAOBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA) {
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    // 구매하는 경우의 채팅방 조회
    public List<ChatRoomDAO> exec(Long userId){
        return chatRoomRepositoryJPA.findByBuyerId(userId);
    }

}
