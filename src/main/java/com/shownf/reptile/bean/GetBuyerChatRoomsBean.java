package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseChatRoomDTO;
import com.shownf.reptile.Model.entity.ChatRoomDAO;
import com.shownf.reptile.bean.small.CreateChatRoomsDTOBean;
import com.shownf.reptile.bean.small.GetBuyerChatRoomsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetBuyerChatRoomsBean {

    GetBuyerChatRoomsDAOBean getBuyerChatRoomsDAOBean;
    CreateChatRoomsDTOBean createChatRoomsDTOBean;

    @Autowired
    public GetBuyerChatRoomsBean(GetBuyerChatRoomsDAOBean getBuyerChatRoomsDAOBean, CreateChatRoomsDTOBean createChatRoomsDTOBean) {
        this.getBuyerChatRoomsDAOBean = getBuyerChatRoomsDAOBean;
        this.createChatRoomsDTOBean = createChatRoomsDTOBean;
    }

    // 구매자 채팅방 전체 조회
    public List<ResponseChatRoomDTO> exec(String userId){

        // userId와 buyerId 일치하는 채팅방 객체 찾기
        List<ChatRoomDAO> chatRoomDAOs = getBuyerChatRoomsDAOBean.exec(userId);

        // DAO 객체 DTO 로 반환
        return createChatRoomsDTOBean.exec(chatRoomDAOs);
    }
}
