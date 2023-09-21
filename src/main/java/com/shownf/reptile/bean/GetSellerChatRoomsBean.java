package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseChatRoomDTO;
import com.shownf.reptile.Model.entity.ChatRoomDAO;
import com.shownf.reptile.bean.small.CreateChatRoomsDTOBean;
import com.shownf.reptile.bean.small.GetSellerChatRoomsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSellerChatRoomsBean {

    GetSellerChatRoomsDAOBean getSellerChatRoomsDAOBean;
    CreateChatRoomsDTOBean createChatRoomsDTOBean;

    @Autowired
    public GetSellerChatRoomsBean(GetSellerChatRoomsDAOBean getSellerChatRoomsDAOBean, CreateChatRoomsDTOBean createChatRoomsDTOBean) {
        this.getSellerChatRoomsDAOBean = getSellerChatRoomsDAOBean;
        this.createChatRoomsDTOBean = createChatRoomsDTOBean;
    }

    // 판매자 채팅방 전체 조회
    public List<ResponseChatRoomDTO> exec(String userId){

        // userId와 sellerId가 일치하는 채팅방 객체 찾기
        List<ChatRoomDAO> chatRoomDAOs = getSellerChatRoomsDAOBean.exec(userId);

        // DAO 객체 DTO 로 반환
        return createChatRoomsDTOBean.exec(chatRoomDAOs);
    }
}
