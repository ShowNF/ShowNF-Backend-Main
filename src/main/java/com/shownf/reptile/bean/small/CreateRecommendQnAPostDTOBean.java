package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.ResponseRecommendQnAPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateRecommendQnAPostDTOBean {

    public ResponseRecommendQnAPostGetDTO exec(QnAPostMeta qnAPostMeta) {
        ResponseRecommendQnAPostGetDTO responseRecommendQnAPostGetDTO = new ResponseRecommendQnAPostGetDTO();

        responseRecommendQnAPostGetDTO.setQnaPostId(qnAPostMeta.getQnaPostId());
        responseRecommendQnAPostGetDTO.setTitle(qnAPostMeta.getTitle());
        responseRecommendQnAPostGetDTO.setCommentCount(qnAPostMeta.getCommentCount());
        responseRecommendQnAPostGetDTO.setHeartCount(qnAPostMeta.getHeartCount());
        responseRecommendQnAPostGetDTO.setViewCount(qnAPostMeta.getViewCount());

        return responseRecommendQnAPostGetDTO;
    }

    public List<ResponseRecommendQnAPostGetDTO> exec(List<QnAPostMeta> qnAPostMetas) {

        List<ResponseRecommendQnAPostGetDTO> responseRecommendQnAPostGetDTOS = new ArrayList<>();

        for (QnAPostMeta qnAPostMeta : qnAPostMetas) {
            responseRecommendQnAPostGetDTOS.add(exec(qnAPostMeta));
        }

        return responseRecommendQnAPostGetDTOS;
    }
}
