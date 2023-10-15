package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.ResponseChatDTO;
import com.shownf.reptile.bean.SseEmittersBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class SseController {

    private final SseEmittersBean sseEmittersBean;

    @Autowired
    public SseController(SseEmittersBean sseEmittersBean) {
        this.sseEmittersBean = sseEmittersBean;
    }

    @GetMapping(value = "/connect/chat-room/{chatRoomId}/user/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable String chatRoomId, @PathVariable Long userId) {
        final SseEmitter emitter = new SseEmitter(180000L);
        sseEmittersBean.add(emitter, chatRoomId, userId);
        try {
            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);
    }

    @PostMapping("/chat-event")
    public ResponseEntity<Map<String, Object>> handleChatEvent(@RequestBody ResponseChatDTO chat) {

        Map<String, Object> map = sseEmittersBean.receiveChat(chat);

        return ResponseEntity.ok(map);
    }
}
