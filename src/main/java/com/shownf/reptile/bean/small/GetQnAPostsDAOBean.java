package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.repository.qna.QnAPostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostsDAOBean {

    QnAPostRepositoryJPA qnAPostRepositoryJPA;

    @Autowired
    public GetQnAPostsDAOBean(QnAPostRepositoryJPA qnAPostRepositoryJPA) {
        this.qnAPostRepositoryJPA = qnAPostRepositoryJPA;
    }

    // 유저 QnA 게시물 전체 조회
    public Page<QnAPostDAO> exec(Long userid, Pageable pageable){
        return qnAPostRepositoryJPA.findByUserId(userid, pageable);
    }
}
