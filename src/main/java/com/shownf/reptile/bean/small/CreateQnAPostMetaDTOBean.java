package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import org.springframework.stereotype.Component;

@Component
public class CreateQnAPostMetaDTOBean {

    // QnA 게시물 조회시 DTO 생성
    public ResponseQnAPostMetaDTO exec(QnAPostMeta qnAPostMeta){
        ResponseQnAPostMetaDTO responseQnAPostMetaDTO = new ResponseQnAPostMetaDTO();

        // DTO 객체에 게시물 정보 넘기기
        responseQnAPostMetaDTO.setQnaPostId(qnAPostMeta.getQnaPostId());
        responseQnAPostMetaDTO.setUserId(qnAPostMeta.getUserId());
        responseQnAPostMetaDTO.setTitle(qnAPostMeta.getTitle());
        responseQnAPostMetaDTO.setImageUrl(qnAPostMeta.getImageUrl());
        responseQnAPostMetaDTO.setContent(qnAPostMeta.getContent());
        responseQnAPostMetaDTO.setUploadTime(qnAPostMeta.getUploadTime());
        responseQnAPostMetaDTO.setUpdateTime(qnAPostMeta.getUpdateTime());
        responseQnAPostMetaDTO.setHeartCount(qnAPostMeta.getHeartCount());
        responseQnAPostMetaDTO.setCommentCount(qnAPostMeta.getCommentCount());
        responseQnAPostMetaDTO.setViewCount(qnAPostMeta.getViewCount());

        // DTO 반환
        return responseQnAPostMetaDTO;
    }
}
