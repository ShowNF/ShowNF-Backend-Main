package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.repository.qna.QnAPostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetQnAPostMetaDAOBean {

    QnAPostMetaRepositoryJPA qnAPostMetaRepositoryJPA;

    @Autowired
    public GetQnAPostMetaDAOBean(QnAPostMetaRepositoryJPA qnAPostMetaRepositoryJPA) {
        this.qnAPostMetaRepositoryJPA = qnAPostMetaRepositoryJPA;
    }

    // Get the QnA Post Meta
    public QnAPostMeta exec(Long qnaPostId) {
        return qnAPostMetaRepositoryJPA.findById(qnaPostId).orElse(null);
    }

    // Get QnA Post Metas
    public List<QnAPostMeta> exec(List<Long> qnaPostIds) {
        return qnAPostMetaRepositoryJPA.findAllById(qnaPostIds);
    }

    // Get the top 4 recommended QnA Posts
    public List<QnAPostMeta> exec() {
        return qnAPostMetaRepositoryJPA.findTop4ByOrderByHeartCountDesc();
    }

    // Get all recommended QnA Posts
    public List<QnAPostMeta> exec(String search, String searchType) {

        List<QnAPostMeta> qnaPostMetas;
        if (searchType.equals("hot"))
            qnaPostMetas = qnAPostMetaRepositoryJPA.findAllByOrderByHeartCountDesc();
        else if (searchType.equals("new"))
            qnaPostMetas = qnAPostMetaRepositoryJPA.findAllByOrderByUploadTimeDesc();
        else
            return null;

        return qnaPostMetas.stream()
                .filter(qnAPostMeta -> qnAPostMeta.getTitle().contains(search))
                .collect(Collectors.toList());
    }
}
