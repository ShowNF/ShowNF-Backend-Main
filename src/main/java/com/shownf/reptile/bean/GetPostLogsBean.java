package com.shownf.reptile.bean;

import com.amazonaws.ResponseMetadata;
import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.bean.small.GetPostLogsDAOBean;
import com.shownf.reptile.bean.small.GetPostMetaDAOsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetPostLogsBean {

    GetPostLogsDAOBean getPostLogsDAOBean;
    GetPostMetaDAOsBean getPostMetaDAOsBean;

    @Autowired
    public GetPostLogsBean(GetPostLogsDAOBean getPostLogsDAOBean, GetPostMetaDAOsBean getPostMetaDAOsBean) {
        this.getPostLogsDAOBean = getPostLogsDAOBean;
        this.getPostMetaDAOsBean = getPostMetaDAOsBean;
    }

    // Get posts log
    public List<ResponsePostMetaDTO> exec(Long userId){

        // userId로 최근 조회한 게시물 가져오기
        List<PostLogDAO> postLogDAOs = getPostLogsDAOBean.exec(userId);

        // 최근 조회한 게시물에서 postId list 뽑기
        List<Long> postIdList = postLogDAOs.stream()
                .map(PostLogDAO::getPostId)
                .collect(Collectors.toList());

        // postId로 메타데이터 가져오기
        return getPostMetaDAOsBean.exec(postIdList);
    }
}
