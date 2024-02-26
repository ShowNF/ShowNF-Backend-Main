package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CheckDeleteQnAPostDAOBean {

    // QnA 게시물 삭제 여부 확인
    public Page<QnAPostDAO> exec(Page<QnAPostDAO> qnAPostDAOs) {
        List<QnAPostDAO> filteredQnAPostDAOS = qnAPostDAOs.getContent().stream()
                .filter(qnaPostDAO -> !qnaPostDAO.isDeleteCheck())
                .collect(Collectors.toList());

        return new PageImpl<>(filteredQnAPostDAOS, qnAPostDAOs.getPageable(), filteredQnAPostDAOS.size());
    }
}
