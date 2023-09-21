package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.Model.DTO.ResponseChatRoomDTO;
import com.shownf.reptile.bean.GetSellerChatRoomsBean;
import com.shownf.reptile.bean.SaveChatRoomBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    SaveChatRoomBean saveChatRoomBean;
    GetSellerChatRoomsBean getSellerChatRoomsBean;

    @Autowired
    public ChatRoomService(SaveChatRoomBean saveChatRoomBean, GetSellerChatRoomsBean getSellerChatRoomsBean) {
        this.saveChatRoomBean = saveChatRoomBean;
        this.getSellerChatRoomsBean = getSellerChatRoomsBean;
    }

    // 판매자 채팅방 전체 조회
    public List<ResponseChatRoomDTO> getSellerChatRooms(String userId){
        return getSellerChatRoomsBean.exec(userId);
    }

    // 채팅방 저장
    public Long saveChatRoom(RequestChatRoomSaveDTO requestChatRoomSaveDTO){
        return saveChatRoomBean.exec(requestChatRoomSaveDTO);
    }
}
