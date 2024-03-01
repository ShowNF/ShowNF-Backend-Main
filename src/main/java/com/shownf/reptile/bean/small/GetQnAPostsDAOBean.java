package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.repository.qna.QnAPostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAPostsDAOBean {

    QnAPostMetaRepositoryJPA qnAPostMetaRepositoryJPA;

    @Autowired
    public GetQnAPostsDAOBean(QnAPostMetaRepositoryJPA qnAPostMetaRepositoryJPA) {
        this.qnAPostMetaRepositoryJPA = qnAPostMetaRepositoryJPA;
    }

    // QnA 게시물 전체 조회
    public Page<QnAPostMeta> exec(Pageable pageable){
        return qnAPostMetaRepositoryJPA.findAll(pageable);
    }

    // 유저 QnA 게시물 전체 조회
    public Page<QnAPostMeta> exec(Long userid, Pageable pageable){
        return qnAPostMetaRepositoryJPA.findByUserId(userid, pageable);
    }

    // 게시물 아이디로 게시물 찾기
    public Page<QnAPostMeta> exec(List<Long> qnaPostIds, Pageable pageable) {
        return qnAPostMetaRepositoryJPA.findAllByQnaPostIdIn(qnaPostIds, pageable);
    }
}
