package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseChatDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class SseEmittersBean {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter, int chatRoomId) {
        this.emitters.add(chatRoomId, emitter);
        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emitters.size());
        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
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
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("chat")
                        .data(map));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        return map;
    }

}
