package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestReplyHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestReplyHeartSaveDTO;
import com.shownf.reptile.bean.DeleteReplyHeartBean;
import com.shownf.reptile.bean.GetReplyIdsBean;
import com.shownf.reptile.bean.SaveReplyHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyHeartService {

    GetReplyIdsBean getReplyIdsBean;
    SaveReplyHeartBean saveReplyHeartBean;
    DeleteReplyHeartBean deleteReplyHeartBean;

    @Autowired
    public ReplyHeartService(GetReplyIdsBean getReplyIdsBean, SaveReplyHeartBean saveReplyHeartBean, DeleteReplyHeartBean deleteReplyHeartBean) {
        this.getReplyIdsBean = getReplyIdsBean;
        this.saveReplyHeartBean = saveReplyHeartBean;
        this.deleteReplyHeartBean = deleteReplyHeartBean;
    }

    // 좋아요 누른 대댓글 아이디 전체 조회
    public List<Long> getReplyIds(Long userId){
        return getReplyIdsBean.exec(userId);
    }


    // 대댓글 좋아요 저장
    public Long saveReplyHeart(RequestReplyHeartSaveDTO requestReplyHeartSaveDTO){
        return saveReplyHeartBean.exec(requestReplyHeartSaveDTO);
    }

    // 대댓글 좋아요 삭제
    public Long deleteReplyHeart(RequestReplyHeartDeleteDTO requestReplyHeartDeleteDTO){
        return deleteReplyHeartBean.exec(requestReplyHeartDeleteDTO);
    }
}
