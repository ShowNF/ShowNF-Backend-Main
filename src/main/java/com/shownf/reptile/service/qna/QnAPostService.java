package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.bean.GetQnAPostBean;
import com.shownf.reptile.bean.GetQnAPostsBean;
import com.shownf.reptile.bean.SaveQnAPostBean;
import com.shownf.reptile.bean.UpdateQnAPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class QnAPostService {

    GetQnAPostBean getQnAPostBean;
    GetQnAPostsBean getQnAPostsBean;
    SaveQnAPostBean saveQnAPostBean;
    UpdateQnAPostBean updateQnAPostBean;


    @Autowired
    public QnAPostService(GetQnAPostBean getQnAPostBean, GetQnAPostsBean getQnAPostsBean, SaveQnAPostBean saveQnAPostBean, UpdateQnAPostBean updateQnAPostBean) {
        this.getQnAPostBean = getQnAPostBean;
        this.getQnAPostsBean = getQnAPostsBean;
        this.saveQnAPostBean = saveQnAPostBean;
        this.updateQnAPostBean = updateQnAPostBean;
    }

    // QnA 게시물 조회
    public ResponseQnAPostGetDTO getQnAPostDAO(Long qnaPostId){
        return getQnAPostBean.exec(qnaPostId);
    }

    // 핫 QnA 게시물 Page 형태로 전체 조회
    public Page<ResponseQnAPostGetDTO> getQnAPostsDAO(Pageable pageable){
        return getQnAPostsBean.exec(pageable);
    }

    // 마이페이지 유저 QnA 게시물 Page 형태로 전체 조회
    public Page<ResponseQnAPostGetDTO> getQnAPostsDAO(Long userId, Pageable pageable){
        return getQnAPostsBean.exec(userId, pageable);
    }

    // QnA 게시물 저장
    public Long saveQnAPostDAO(RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        return saveQnAPostBean.exec(requestQnAPostSaveDTO);
    }

    // QnA 게시물 수정
    public Long updateQnAPostDAO(RequestQnAPostUpdateDTO requestQnAPostUpdateDTO, HttpServletRequest request){
        return updateQnAPostBean.exec(requestQnAPostUpdateDTO, request);
    }

}
