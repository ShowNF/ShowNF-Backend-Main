package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.ReplyHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReplyHeartsDAOBean {

    ReplyHeartRepositoryJPA replyHeartRepositoryJPA;

    @Autowired
    public GetReplyHeartsDAOBean(ReplyHeartRepositoryJPA replyHeartRepositoryJPA) {
        this.replyHeartRepositoryJPA = replyHeartRepositoryJPA;
    }

    // 좋아요 누른 대댓글 가져오기
    public List<ReplyHeartDAO> exec(Long userId){
        return replyHeartRepositoryJPA.findByUserId(userId);
    }
}
