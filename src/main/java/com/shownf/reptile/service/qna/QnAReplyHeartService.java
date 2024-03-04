package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAReplyHeartSaveDTO;
import com.shownf.reptile.bean.DeleteQnAReplyHeartBean;
import com.shownf.reptile.bean.GetQnAReplyIdsBean;
import com.shownf.reptile.bean.SaveQnAReplyHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QnAReplyHeartService {

    GetQnAReplyIdsBean getQnAReplyIdsBean;
    SaveQnAReplyHeartBean saveQnAReplyHeartBean;
    DeleteQnAReplyHeartBean deleteQnAReplyHeartBean;

    @Autowired
    public QnAReplyHeartService(GetQnAReplyIdsBean getQnAReplyIdsBean, SaveQnAReplyHeartBean saveQnAReplyHeartBean, DeleteQnAReplyHeartBean deleteQnAReplyHeartBean) {
        this.getQnAReplyIdsBean = getQnAReplyIdsBean;
        this.saveQnAReplyHeartBean = saveQnAReplyHeartBean;
        this.deleteQnAReplyHeartBean = deleteQnAReplyHeartBean;
    }

    // 좋아요 누른 게시물 아이디 전체 조회
    public List<Long> getQnAReplyIds(Long userId){
        return getQnAReplyIdsBean.exec(userId);
    }

    // QnA 대댓글 좋아요 저장
    public Long saveQnAReplyHeart(RequestQnAReplyHeartSaveDTO requestQnAReplyHeartSaveDTO){
        return saveQnAReplyHeartBean.exec(requestQnAReplyHeartSaveDTO);
    }

    // QnA 대댓글 좋아요 삭제
    public Long deleteQnAReplyHeart(RequestQnAReplyHeartDeleteDTO requestQnAReplyHeartDeleteDTO, HttpServletRequest request){
        return deleteQnAReplyHeartBean.exec(requestQnAReplyHeartDeleteDTO, request);
    }
}
