package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.Model.DTO.ResponseChatRoomDTO;
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

    // 판매자 채팅방 전체 조회
    @GetMapping("chatRoom/seller/{userId}")
    public List<ResponseChatRoomDTO> getSellerChatRooms(@PathVariable String userId){
        return chatRoomService.getSellerChatRooms(userId);
    }

    // 채팅방 저장
    @PostMapping("chatRoom")
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
