package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.bean.SaveChatRoomBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

    SaveChatRoomBean saveChatRoomBean;

    @Autowired
    public ChatRoomService(SaveChatRoomBean saveChatRoomBean) {
        this.saveChatRoomBean = saveChatRoomBean;
    }

    // 채팅방 저장
    public Long saveChatRoom(RequestChatRoomSaveDTO requestChatRoomSaveDTO){
        return saveChatRoomBean.exec(requestChatRoomSaveDTO);
    }
}
