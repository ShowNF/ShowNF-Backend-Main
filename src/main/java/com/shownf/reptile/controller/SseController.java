package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.ResponseChatDTO;
import com.shownf.reptile.bean.SseEmittersBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class SseController {

    private final SseEmittersBean sseEmittersBean;

    @Autowired
    public SseController(SseEmittersBean sseEmittersBean) {
        this.sseEmittersBean = sseEmittersBean;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() {
        SseEmitter emitter = new SseEmitter();
        sseEmittersBean.add(emitter);
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
    public ResponseEntity<ResponseChatDTO> handleChatEvent(@RequestBody ResponseChatDTO chat) {

        /*String chatId = chat.getChatId();
        String contentId = chat.getContentId();
        String senderId = chat.getSenderId();
        String senderName = chat.getSenderName();
        String content = chat.getContent();
        long timestamp = chat.getTimestamp();*/


        return ResponseEntity.ok(chat);
    }
}
