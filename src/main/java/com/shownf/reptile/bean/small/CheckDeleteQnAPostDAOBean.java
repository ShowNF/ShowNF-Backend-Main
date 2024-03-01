package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckDeleteQnAPostDAOBean {

    // QnA 게시물 삭제 여부 확인
    public Page<QnAPostMeta> exec(Page<QnAPostMeta> qnAPostMetas) {
        List<QnAPostMeta> filteredQnAPostMetas = qnAPostMetas.getContent().stream()
                .filter(qnAPostMeta -> !qnAPostMeta.isDeleteCheck())
                .collect(Collectors.toList());

        return new PageImpl<>(filteredQnAPostMetas, qnAPostMetas.getPageable(), filteredQnAPostMetas.size());
    }
}
