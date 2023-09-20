package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestChatRoomSaveDTO;
import com.shownf.reptile.bean.small.CreateUniqueIdBean;
import com.shownf.reptile.bean.small.SaveChatRoomDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChatRoomBean {

    CreateUniqueIdBean createUniqueIdBean;
    SaveChatRoomDAOBean saveChatRoomDAOBean;

    @Autowired
    public SaveChatRoomBean(CreateUniqueIdBean createUniqueIdBean, SaveChatRoomDAOBean saveChatRoomDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.saveChatRoomDAOBean = saveChatRoomDAOBean;
    }

    // 채팅방 정보 저장
    public Long exec(RequestChatRoomSaveDTO requestChatRoomSaveDTO){

        // 고유 아이디 생성
        Long uniqueId = createUniqueIdBean.exec();

        // 채팅방 저장
        saveChatRoomDAOBean.exec(uniqueId, requestChatRoomSaveDTO);

        // 채팅방 uniqueId 반환
        return uniqueId;
    }
}
