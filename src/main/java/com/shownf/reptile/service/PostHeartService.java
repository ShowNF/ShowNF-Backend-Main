package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestPostHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostHeartSaveDTO;
import com.shownf.reptile.bean.DeletePostHeartBean;
import com.shownf.reptile.bean.GetPostIdsBean;
import com.shownf.reptile.bean.SavePostHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostHeartService {

    GetPostIdsBean getPostIdsBean;
    SavePostHeartBean savePostHeartBean;
    DeletePostHeartBean deletePostHeartBean;

    @Autowired
    public PostHeartService(GetPostIdsBean getPostIdsBean, SavePostHeartBean savePostHeartBean, DeletePostHeartBean deletePostHeartBean) {
        this.getPostIdsBean = getPostIdsBean;
        this.savePostHeartBean = savePostHeartBean;
        this.deletePostHeartBean =deletePostHeartBean;
    }

    // 좋아요 누른 게시물 아이디 전체 조회
    public List<Long> getPostIds(Long userId){
        return getPostIdsBean.exec(userId);
    }

    // 게시물 좋아요 저장
    public Long savePostHeart(RequestPostHeartSaveDTO requestPostHeartDTO){
        return savePostHeartBean.exec(requestPostHeartDTO);
    }

    // 게시물 좋아요 삭제
    public Long deletePostHeart(RequestPostHeartDeleteDTO requestPostHeartDeleteDTO){
        return deletePostHeartBean.exec(requestPostHeartDeleteDTO);
    }
}
