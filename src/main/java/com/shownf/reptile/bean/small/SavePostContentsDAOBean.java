package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.repository.PostContentRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SavePostContentsDAOBean {

    PostContentRepositoryJPA postContentRepositoryJPA;
    CreateUniqueIdBean createUniqueIdBean;

    @Autowired
    public SavePostContentsDAOBean(PostContentRepositoryJPA postContentRepositoryJPA, CreateUniqueIdBean createUniqueIdBean) {
        this.postContentRepositoryJPA = postContentRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
    }

    // 게시물 postContent 저장
    public void exec(PostContentDAO postContentDAO){
        postContentRepositoryJPA.save(postContentDAO);
    }

    // 게시물 postContent 전부 저장
    public void exec(List<PostContentDAO> postContentDAOs){
        for (PostContentDAO postContentDAO : postContentDAOs)
            exec(postContentDAO);
    }

    // 게시물 저장시 postContent 저장
    public List<Map<Integer, Long>> exec(Long postId, RequestPostSaveDTO requestPostSaveDTO){

        // 반환하려는 PostContent List
        List<Map<Integer, Long>> postContents = new ArrayList<>();


        for (Map<String, String> contentMap : requestPostSaveDTO.getContent()) {

            // postContentId 생성
            Long postContentId = createUniqueIdBean.exec();

            // postContent index
            Integer index = Integer.parseInt(contentMap.get("index"));

            // 이미지 Url
            String imageUrl = contentMap.get("imageUrl");

            // 내용
            String content = contentMap.get("content");

            // 소프트 딜리트
            boolean deleteCheck = false;

            // postContent 저장
            exec(new PostContentDAO(postContentId, postId, imageUrl, content, index, deleteCheck));

            Map<Integer, Long> postContentIndex = Collections.singletonMap(index, postContentId);

            postContents.add(postContentIndex);
        }

        return postContents;
    }
}
