package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostHeartSaveDTO;
import com.shownf.reptile.bean.DeleteQnAPostHeartBean;
import com.shownf.reptile.bean.GetQnAPostIdsBean;
import com.shownf.reptile.bean.SaveQnAPostHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAPostHeartService {

    GetQnAPostIdsBean getQnAPostIdsBean;
    SaveQnAPostHeartBean saveQnAPostHeartBean;
    DeleteQnAPostHeartBean deleteQnAPostHeartBean;

    @Autowired
    public QnAPostHeartService(GetQnAPostIdsBean getQnAPostIdsBean, SaveQnAPostHeartBean saveQnAPostHeartBean, DeleteQnAPostHeartBean deleteQnAPostHeartBean) {
        this.getQnAPostIdsBean = getQnAPostIdsBean;
        this.saveQnAPostHeartBean = saveQnAPostHeartBean;
        this.deleteQnAPostHeartBean = deleteQnAPostHeartBean;
    }

    // 좋아요 누른 QnA 게시물 아이디 전체 조회
    public List<Long> getQnAPostIds(Long userId){
        return getQnAPostIdsBean.exec(userId);
    }

    // QnA 게시물 좋아요 저장
    public Long saveQnAPostHeart(RequestQnAPostHeartSaveDTO requestQnAPostHeartSaveDTO){
        return saveQnAPostHeartBean.exec(requestQnAPostHeartSaveDTO);
    }

    // QnA 게시물 좋아요 삭제
    public Long deleteQnAPostHeart(RequestQnAPostHeartDeleteDTO requestQnAPostHeartSaveDTO){
        return deleteQnAPostHeartBean.exec(requestQnAPostHeartSaveDTO);
    }
}