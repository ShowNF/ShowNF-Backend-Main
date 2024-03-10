package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseRecommendPostGetDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostContentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateRecommendPostDTOBean {

    GetPostContentDAOBean getPostContentDAOBean;

    @Autowired
    public CreateRecommendPostDTOBean(GetPostContentDAOBean getPostContentDAOBean) {
        this.getPostContentDAOBean = getPostContentDAOBean;
    }

    public ResponseRecommendPostGetDTO exec(PostMeta postMeta) {
        ResponseRecommendPostGetDTO responseRecommendPostGetDTO = new ResponseRecommendPostGetDTO();

        responseRecommendPostGetDTO.setPostId(postMeta.getPostId());
        responseRecommendPostGetDTO.setTitle(postMeta.getTitle());
        responseRecommendPostGetDTO.setUserId(postMeta.getUserId());
        PostContentDAO postContentDAO = getPostContentDAOBean.exec(Long.valueOf(postMeta.getContent()));
        responseRecommendPostGetDTO.setImageUrl(postContentDAO.getImageUrl());

        return responseRecommendPostGetDTO;
    }

    public List<ResponseRecommendPostGetDTO> exec(List<PostMeta> postMetas) {

        List<ResponseRecommendPostGetDTO> responseRecommendPostGetDTOS = new ArrayList<>();

        for (PostMeta postMeta : postMetas) {
            responseRecommendPostGetDTOS.add(exec(postMeta));
        }

        return responseRecommendPostGetDTOS;
    }
}
