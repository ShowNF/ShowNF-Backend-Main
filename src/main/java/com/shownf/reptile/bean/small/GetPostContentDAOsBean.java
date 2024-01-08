package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.repository.PostContentRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetPostContentDAOsBean {

    PostContentRepositoryJPA postContentRepositoryJPA;

    @Autowired
    public GetPostContentDAOsBean(PostContentRepositoryJPA postContentRepositoryJPA) {
        this.postContentRepositoryJPA = postContentRepositoryJPA;
    }

    // Get the post content
    public List<PostContentDAO> exec(Long postId){
        return postContentRepositoryJPA.findByPostId(postId);
    }

    // Get the post contents
    public String exec(Long postId, String content){

        // 문자열에서 숫자 추출
        String[] numbersArray = content.replaceAll("[^0-9,]", "").split(",");

        // String 배열을 Integer 리스트로 변환
        List<Long> numbersList = new ArrayList<>();
        for (String number : numbersArray) {
            numbersList.add(Long.parseLong(number));
        }

        // postContent 가져오기
        List<PostContentDAO> postContentDAOs = exec(postId);

        // content 일치 여부
        for (PostContentDAO postContentDAO: postContentDAOs)
            numbersList.remove(postContentDAO.getPostContentId());
        if (!numbersList.isEmpty())
            return null;

        // 반환할 content
        List<Map<String, String>> contents = new ArrayList<>();

        for (PostContentDAO postContentDAO : postContentDAOs){

            Map<String, String> postContent = new HashMap<>();

            postContent.put("postContentId", postContentDAO.getPostContentId().toString());
            postContent.put("imageUrl", postContentDAO.getImageUrl());
            postContent.put("content", postContentDAO.getContent());

            contents.add(postContent);
        }

        // postContent 반환
        ObjectMapper objectMapper = new ObjectMapper();
        String newContent = "";
        try {
            newContent = objectMapper.writeValueAsString(contents);
        }catch (IOException e){
            e.printStackTrace();
        }

        return newContent;
    }
}
