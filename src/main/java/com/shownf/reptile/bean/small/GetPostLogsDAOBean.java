package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.repository.PostLogRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostLogsDAOBean {

    PostLogRepositoryJPA postLogRepositoryJPA;

    @Autowired
    public GetPostLogsDAOBean(PostLogRepositoryJPA postLogRepositoryJPA) {
        this.postLogRepositoryJPA = postLogRepositoryJPA;
    }

    // Get post logs
    public List<PostLogDAO> exec(Long userId){
        Sort sort = Sort.by(Sort.Order.desc("viewTime"));
        return postLogRepositoryJPA.findByUserId(userId, sort);
    }
}
