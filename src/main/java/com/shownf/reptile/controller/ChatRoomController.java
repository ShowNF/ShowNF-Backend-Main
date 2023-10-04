package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.Model.DTO.ResponseChatRoomDTO;
import com.shownf.reptile.Model.DTO.ResponseChatDTO;
import com.shownf.reptile.service.ChatRoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ChatRoomController {

    ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/chat-event")
    public ResponseEntity<String> handleChatEvent(@RequestBody ResponseChatDTO chat) {

        String chatId = chat.getChatId();
        String contentId = chat.getContentId();
        String senderId = chat.getSenderId();
        String senderName = chat.getSenderName();
        String content = chat.getContent();
        long timestamp = chat.getTimestamp();


        System.out.println("Received Chat Message:");
        System.out.println("Chat ID: " + chatId);
        System.out.println("Content ID: " + contentId);
        System.out.println("Sender ID: " + senderId);
        System.out.println("Sender Name: " + senderName);
        System.out.println("Content: " + content);
        System.out.println("Timestamp: " + timestamp);

        return ResponseEntity.ok("Chat event received successfully!");
    }


    // 판매자 채팅방 전체 조회
    @ApiOperation(value = "판매자 채팅방 조회", notes = "판매하는 글의 채팅방을 조회한다.")
    @GetMapping("chat-room/seller/{userId}")
    public List<ResponseChatRoomDTO> getSellerChatRooms(@PathVariable String userId){
        return chatRoomService.getSellerChatRooms(userId);
    }

    // 구매자 채팅방 전체 조회
    @ApiOperation(value = "구매자 채팅방 조회", notes = "구매하는 글의 채팅방을 조회한다.")
    @GetMapping("chat-room/buyer/{userId}")
    public List<ResponseChatRoomDTO> getBuyerChatRooms(@PathVariable String userId){
        return chatRoomService.getBuyerChatRooms(userId);
    }

    // 채팅방 저장
    @PostMapping("chat-room")
    public ResponseEntity<Map<String, Object>> saveChatRoom(@RequestBody RequestChatRoomSaveDTO requestChatRoomSaveDTO){
        Long uniqueId = chatRoomService.saveChatRoom(requestChatRoomSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (uniqueId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (uniqueId != null) ? "Save Success" : "Save Fail");
        requestMap.put("uniqueId", uniqueId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
