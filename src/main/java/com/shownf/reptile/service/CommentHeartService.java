package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.bean.DeleteCommentHeartBean;
import com.shownf.reptile.bean.GetCommentIdsBean;
import com.shownf.reptile.bean.SaveCommentHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentHeartService {

    GetCommentIdsBean getCommentIdsBean;
    SaveCommentHeartBean saveCommentHeartBean;
    DeleteCommentHeartBean deleteCommentHeartBean;

    @Autowired
    public CommentHeartService(GetCommentIdsBean getCommentIdsBean, SaveCommentHeartBean saveCommentHeartBean, DeleteCommentHeartBean deleteCommentHeartBean) {
        this.getCommentIdsBean = getCommentIdsBean;
        this.saveCommentHeartBean = saveCommentHeartBean;
        this.deleteCommentHeartBean = deleteCommentHeartBean;
    }

    // 좋아요 누른 전체 댓글 아이디 가져오기
    public List<Long> getCommentIds(Long userId){
        return getCommentIdsBean.exec(userId);
    }

    // 댓글 좋아요 저장
    public Long saveCommentHeart(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){
        return saveCommentHeartBean.exec(requestCommentHeartSaveDTO);
    }

    // 댓글 좋아요 삭제
    public Long deleteCommentHeart(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){
        return deleteCommentHeartBean.exec(requestCommentHeartDeleteDTO);
    }
}
