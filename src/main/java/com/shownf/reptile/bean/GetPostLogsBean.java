package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.bean.small.GetPostLogsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetPostLogsBean {

    GetPostLogsDAOBean getPostLogsDAOBean;

    @Autowired
    public GetPostLogsBean(GetPostLogsDAOBean getPostLogsDAOBean) {
        this.getPostLogsDAOBean = getPostLogsDAOBean;
    }

    // Get posts log
    public List<Long> exec(Long userId){

        // userId로 최근 조회한 게시물 가져오기
        List<PostLogDAO> postLogDAOs = getPostLogsDAOBean.exec(userId);

        // 최근 조회한 게시물에서 postId list 뽑기
        return postLogDAOs.stream()
                .map(PostLogDAO::getPostId)
                .collect(Collectors.toList());
    }
}
