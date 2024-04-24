package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseChatRoomDTO;
import com.shownf.reptile.Model.entity.ChatRoomDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateChatRoomsDTOBean {

    public List<ResponseChatRoomDTO> exec(List<ChatRoomDAO> chatRoomDAOs){

        List<ResponseChatRoomDTO> responseChatRoomDTOs = new ArrayList<>();

        // DTO 객체에 게시물 정보 넘기기
        for (ChatRoomDAO chatRoomDAO : chatRoomDAOs){
            ResponseChatRoomDTO responseChatRoomDTO = new ResponseChatRoomDTO();

            responseChatRoomDTO.setUniqueId(chatRoomDAO.getUniqueId());
            responseChatRoomDTO.setChatRoomId(chatRoomDAO.getChatRoomId());
            responseChatRoomDTO.setSellerId(chatRoomDAO.getSellerId());
            responseChatRoomDTO.setBuyerId(chatRoomDAO.getBuyerId());
            responseChatRoomDTO.setUploadTime(chatRoomDAO.getUploadTime());

            responseChatRoomDTOs.add(responseChatRoomDTO);
        }
        return responseChatRoomDTOs;
    }
}
