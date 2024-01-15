package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPostMetaDAOsBean {

    PostMetaRepositoryJPA postMetaRepositoryJPA;
    CreatePostMetaDTOBean createPostMetaDTOBean;

    @Autowired
    public GetPostMetaDAOsBean(PostMetaRepositoryJPA postMetaRepositoryJPA, CreatePostMetaDTOBean createPostMetaDTOBean) {
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
        this.createPostMetaDTOBean = createPostMetaDTOBean;
    }

    // Get postMetas
    public List<ResponsePostMetaDTO> exec(List<Long> postIdList){

        List<PostMeta> postMetas = postMetaRepositoryJPA.findAllById(postIdList);


        List<ResponsePostMetaDTO> responsePostMetaDTOList = new ArrayList<>();
        for (PostMeta postMeta : postMetas){
            ResponsePostMetaDTO responsePostMetaDTO = createPostMetaDTOBean.exec(postMeta);

            responsePostMetaDTOList.add(responsePostMetaDTO);
        }

        return responsePostMetaDTOList;
    }
}
