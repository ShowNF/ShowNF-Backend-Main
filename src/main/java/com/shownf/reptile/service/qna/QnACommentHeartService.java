package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnACommentHeartSaveDTO;
import com.shownf.reptile.bean.DeleteQnACommentHeartBean;
import com.shownf.reptile.bean.GetQnACommentIdsBean;
import com.shownf.reptile.bean.SaveQnACommentHeartBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QnACommentHeartService {

    GetQnACommentIdsBean getQnACommentIdsBean;
    SaveQnACommentHeartBean saveQnACommentHeartBean;
    DeleteQnACommentHeartBean deleteQnACommentHeartBean;

    @Autowired
    public QnACommentHeartService(GetQnACommentIdsBean getQnACommentIdsBean, SaveQnACommentHeartBean saveQnACommentHeartBean, DeleteQnACommentHeartBean deleteQnACommentHeartBean) {
        this.getQnACommentIdsBean = getQnACommentIdsBean;
        this.saveQnACommentHeartBean = saveQnACommentHeartBean;
        this.deleteQnACommentHeartBean = deleteQnACommentHeartBean;
    }

    // 좋아요 누른 QnA 댓글 아이디 전체 조회
    public List<Long> getQnACommentIds(Long userId) {
        return getQnACommentIdsBean.exec(userId);
    }


    // QnA 댓글 좋아요
    public Long saveQnACommentHeart(RequestQnACommentHeartSaveDTO requestQnACommentHeartSaveDTO) {
        return saveQnACommentHeartBean.exec(requestQnACommentHeartSaveDTO);
    }

    // QnA 댓글 좋아요 삭제
    public Long deleteQnACommentHeart(RequestQnACommentHeartDeleteDTO requestQnACommentHeartDeleteDTO, HttpServletRequest request) {
        return deleteQnACommentHeartBean.exec(requestQnACommentHeartDeleteDTO, request);
    }
}
