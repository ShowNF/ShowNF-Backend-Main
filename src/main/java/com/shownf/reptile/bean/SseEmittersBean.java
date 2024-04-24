package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseChatDTO;
import com.shownf.reptile.Model.entity.ChatRoomDAO;
import com.shownf.reptile.repository.ChatRoomRepositoryJPA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SseEmittersBean {

    private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();

    private ChatRoomRepositoryJPA chatRoomRepositoryJPA;

    @Autowired
    public SseEmittersBean(ChatRoomRepositoryJPA chatRoomRepositoryJPA) {
        this.chatRoomRepositoryJPA = chatRoomRepositoryJPA;
    }

    public SseEmitter add(SseEmitter emitter, String chatRoomId, Long userId) {

        // 채팅방 + 유저아이디 해시
        int key = (chatRoomId + userId).hashCode();

        this.emitters.put(key, emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(key);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        return emitter;
    }

    public Map<String, Object> receiveChat(ResponseChatDTO chat) {
        Map<String, Object> map = new HashMap<>();
        map.put(chat.getContentId(), chat);

        // chatRoomId를 이용하여 해당 채팅방에만 데이터 전송
        String chatRoomId = chat.getChatId();
        ChatRoomDAO chatRoomDAO = chatRoomRepositoryJPA.findByChatRoomId(chatRoomId);

        chatRoomDAO.setContent(chat.getContent());
        chatRoomDAO.setContentUserId(chat.getSenderId());
        chatRoomDAO.setContentUploadTime(LocalDateTime.now());
        chatRoomRepositoryJPA.save(chatRoomDAO);

        // 채팅방 + 유저아이디 해시
        int key;

        if (chatRoomDAO.getBuyerId().equals(chat.getSenderId()))
            key = (chatRoomId + chatRoomDAO.getSellerId()).hashCode();
        else if (chatRoomDAO.getSellerId().equals(chat.getSenderId()))
            key = (chatRoomId + chatRoomDAO.getBuyerId()).hashCode();
        else
            key = 0;

        SseEmitter emitter = emitters.get(key);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .name("chat")
                        .data(map));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

}
