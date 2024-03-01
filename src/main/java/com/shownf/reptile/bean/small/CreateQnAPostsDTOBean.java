package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateQnAPostsDTOBean {

    // QnA 게시물 DAO 객체 DTO 반환
    public Page<Long> exec(Page<QnAPostMeta> qnAPostMetas){

        List<Long> qnaPostIds = new ArrayList<>();

        for (QnAPostMeta qnAPostMeta: qnAPostMetas) {
            qnaPostIds.add(qnAPostMeta.getQnaPostId());
        }

        return new PageImpl<>(qnaPostIds, qnAPostMetas.getPageable(), qnAPostMetas.getTotalElements());

    }
}
