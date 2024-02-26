package com.shownf.reptile.service.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.bean.GetQnAPostBean;
import com.shownf.reptile.bean.GetQnAPostsBean;
import com.shownf.reptile.bean.SaveQnAPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QnAPostService {

    GetQnAPostBean getQnAPostBean;
    GetQnAPostsBean getQnAPostsBean;
    SaveQnAPostBean saveQnAPostBean;


    @Autowired
    public QnAPostService(GetQnAPostBean getQnAPostBean, GetQnAPostsBean getQnAPostsBean, SaveQnAPostBean saveQnAPostBean) {
        this.getQnAPostBean = getQnAPostBean;
        this.getQnAPostsBean = getQnAPostsBean;
        this.saveQnAPostBean = saveQnAPostBean;
    }

    // QnA 게시물 조회
    public ResponseQnAPostGetDTO getQnAPostDAO(Long qnaPostId){
        return getQnAPostBean.exec(qnaPostId);
    }

    // 마이페이지 유저 QnA 게시물 Page 형태로 전체 조회
    public Page<ResponseQnAPostGetDTO> getQnAPostsDAO(Long userId, Pageable pageable){
        return getQnAPostsBean.exec(userId, pageable);
    }

    // QnA 게시물 저장
    public Long saveQnAPostDAO(RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        return saveQnAPostBean.exec(requestQnAPostSaveDTO);
    }

}
