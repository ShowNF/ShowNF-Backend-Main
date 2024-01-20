package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreatePostMetaDTOBean {

    // Create the post meta DTO
    public ResponsePostMetaDTO exec(PostMeta postMeta){

        ResponsePostMetaDTO responsePostMetaDTO = new ResponsePostMetaDTO();

        // DTO 객체에 게시물 정보 넘기기
        responsePostMetaDTO.setPostId(postMeta.getPostId());
        responsePostMetaDTO.setUserId(postMeta.getUserId());
        responsePostMetaDTO.setTitle(postMeta.getTitle());
        responsePostMetaDTO.setContent(postMeta.getContent());
        responsePostMetaDTO.setCategory(postMeta.getCategory().name());
        responsePostMetaDTO.setUploadTime(postMeta.getUploadTime());
        responsePostMetaDTO.setUpdateTime(postMeta.getUpdateTime());
        responsePostMetaDTO.setHeartCount(postMeta.getHeartCount());
        responsePostMetaDTO.setCommentCount(postMeta.getCommentCount());
        responsePostMetaDTO.setViewCount(postMeta.getViewCount());

        return responsePostMetaDTO;
    }

    // Create post meta DTOs
    public List<ResponsePostMetaDTO> exec(List<PostMeta> postMetas){

        List<ResponsePostMetaDTO> responsePostMetaDTOList = new ArrayList<>();

        for (PostMeta postMeta : postMetas){
            ResponsePostMetaDTO responsePostMetaDTO = exec(postMeta);

            responsePostMetaDTOList.add(responsePostMetaDTO);
        }

        return responsePostMetaDTOList;
    }
}
