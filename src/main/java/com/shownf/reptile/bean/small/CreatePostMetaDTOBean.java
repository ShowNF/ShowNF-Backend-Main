package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostContentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreatePostMetaDTOBean {

    GetPostContentDAOBean getPostContentDAOBean;

    @Autowired
    public CreatePostMetaDTOBean(GetPostContentDAOBean getPostContentDAOBean) {
        this.getPostContentDAOBean = getPostContentDAOBean;
    }

    // Create the post meta DTO
    public ResponsePostMetaDTO exec(PostMeta postMeta){

        ResponsePostMetaDTO responsePostMetaDTO = new ResponsePostMetaDTO();

        // DTO 객체에 게시물 정보 넘기기
        responsePostMetaDTO.setPostId(postMeta.getPostId());
        responsePostMetaDTO.setUserId(postMeta.getUserId());
        responsePostMetaDTO.setTitle(postMeta.getTitle());

        // 게시물 내용
        PostContentDAO postContentDAO = getPostContentDAOBean.exec(Long.parseLong(postMeta.getContent()));

        System.out.println("postContentDAO = " + postContentDAO.getContent());
        System.out.println("postContentDAO.getImageUrl() = " + postContentDAO.getImageUrl());
        
        List<Map<String, String>> resultList = new ArrayList<>();
        Map<String, String> postContentMap = new HashMap<>();

        // imageUrl과 content를 Map에 추가
        postContentMap.put("imageUrl", postContentDAO.getImageUrl());
        postContentMap.put("content", postContentDAO.getContent());

        // 리스트에 추가
        resultList.add(postContentMap);

        ObjectMapper objectMapper =new ObjectMapper();

        String content = "";
        try {
            content = objectMapper.writeValueAsString(resultList);
        }catch (IOException e){
            e.printStackTrace();
        }

        responsePostMetaDTO.setContent(content);
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
