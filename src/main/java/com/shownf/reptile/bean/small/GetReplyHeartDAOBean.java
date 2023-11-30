package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestReplyHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplyHeartSaveDTO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.ReplyHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetReplyHeartDAOBean {

    ReplyHeartRepositoryJPA replyHeartRepositoryJPA;

    @Autowired
    public GetReplyHeartDAOBean(ReplyHeartRepositoryJPA replyHeartRepositoryJPA) {
        this.replyHeartRepositoryJPA = replyHeartRepositoryJPA;
    }

    // 대댓글 좋아요 객체 찾기
    public ReplyHeartDAO exec(Long replyHeartId){
        Optional<ReplyHeartDAO> replyHeartOptional = replyHeartRepositoryJPA.findById(replyHeartId);
        return replyHeartOptional.orElse(null);
    }

    // 대댓글 좋아요 중복 배제를 위한 객체 찾기
    public ReplyHeartDAO exec(RequestReplyHeartSaveDTO requestReplyHeartSaveDTO){
        return replyHeartRepositoryJPA.findByUserIdAndReplyId(requestReplyHeartSaveDTO.getUserId(), requestReplyHeartSaveDTO.getReplyId());
    }

    // 대댓글 좋아요 중복 배제를 위한 객체 찾기
    public ReplyHeartDAO exec(RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO){
        return replyHeartRepositoryJPA.findByUserIdAndReplyId(requestReplyHeartDeleteDTO.getUserId(), requestReplyHeartDeleteDTO.getReplyId());
    }
}
