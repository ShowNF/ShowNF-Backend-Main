package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class QnAPostService {

    GetQnAPostBean getQnAPostBean;
    GetQnAPostsBean getQnAPostsBean;
    GetQnAPostUserHeartBean getQnAPostUserHeartBean;
    SaveQnAPostBean saveQnAPostBean;
    UpdateQnAPostBean updateQnAPostBean;
    DeleteQnAPostBean deleteQnAPostBean;


    @Autowired
    public QnAPostService(GetQnAPostBean getQnAPostBean, GetQnAPostsBean getQnAPostsBean, GetQnAPostUserHeartBean getQnAPostUserHeartBean, SaveQnAPostBean saveQnAPostBean, UpdateQnAPostBean updateQnAPostBean, DeleteQnAPostBean deleteQnAPostBean) {
        this.getQnAPostBean = getQnAPostBean;
        this.getQnAPostsBean = getQnAPostsBean;
        this.getQnAPostUserHeartBean = getQnAPostUserHeartBean;
        this.saveQnAPostBean = saveQnAPostBean;
        this.updateQnAPostBean = updateQnAPostBean;
        this.deleteQnAPostBean = deleteQnAPostBean;
    }

    // QnA 게시물 조회
    public ResponseQnAPostGetDTO getQnAPostDAO(Long qnaPostId, Long userId){
        return getQnAPostBean.exec(qnaPostId, userId);
    }

    // 핫 QnA 게시물 Page 형태로 전체 조회
    public Page<Long> getQnAPostsDAO(Pageable pageable){
        return getQnAPostsBean.exec(pageable);
    }

    // 마이페이지 유저 QnA 게시물 Page 형태로 전체 조회
    public Page<Long> getQnAPostsDAO(Long userId, Pageable pageable){
        return getQnAPostsBean.exec(userId, pageable);
    }

    // 유저가 좋아요한 QnA 게시물 Page 형태로 전체 조회
    public Page<Long> getUserQnAPostHearts(Long userId, Pageable pageable){
        return getQnAPostUserHeartBean.exec(userId, pageable);
    }

    // QnA 게시물 저장
    public Long saveQnAPostDAO(RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        return saveQnAPostBean.exec(requestQnAPostSaveDTO);
    }

    // QnA 게시물 수정
    public Long updateQnAPostDAO(RequestQnAPostUpdateDTO requestQnAPostUpdateDTO, HttpServletRequest request){
        return updateQnAPostBean.exec(requestQnAPostUpdateDTO, request);
    }

    // QnA 게시물 삭제
    public Long deleteQnAPostDAO(RequestQnAPostDeleteDTO requestQnAPostDeleteDTO, HttpServletRequest request){
        return deleteQnAPostBean.exec(requestQnAPostDeleteDTO, request);
    }

}
