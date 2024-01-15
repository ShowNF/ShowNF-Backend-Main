package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.DTO.RequestPostUpdateDTO;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UpdatePostContentDAOBean {

    CreateUniqueIdBean createUniqueIdBean;
    SavePostContentsDAOBean savePostContentsDAOBean;

    @Autowired
    public UpdatePostContentDAOBean(CreateUniqueIdBean createUniqueIdBean, SavePostContentsDAOBean savePostContentsDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.savePostContentsDAOBean = savePostContentsDAOBean;
    }

    // Update the post content
    public String exec(RequestPostUpdateDTO requestPostUpdateDTO, PostDAO postDAO){

        // 문자열에서 숫자 추출
        ObjectMapper objectMapper = new ObjectMapper();

        // 게시물 content 변환
        List<Map<Integer, Long>> postContentIndexs = new ArrayList<>();
        try {
            postContentIndexs = objectMapper.readValue(postDAO.getContent(), new TypeReference<List<Map<Integer, Long>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("postContentIndexs = " + postContentIndexs.toString());

        if (postContentIndexs.isEmpty())
            return "[]";

        // key 값 리스트
        List<Integer> postContentDAOkeys = postContentIndexs.stream()
                .flatMap(map -> map.keySet().stream())
                .collect(Collectors.toList());
        System.out.println("postContentDAOkeys = " + postContentDAOkeys.toString());

        // 수정된 postContent list 가져오기
        List<Map<String, String>> contents = requestPostUpdateDTO.getContent();
        System.out.println("contents.toString() = " + contents.toString());

        // contents에서 postContentDAOkeys에 포함되지 않는 key 값을 남겨두기
        List<Map<String, String>> updateContents = contents.stream()
                .filter(map -> !postContentDAOkeys.contains(Integer.parseInt(map.get("postContentIndex"))))
                .collect(Collectors.toList());
        System.out.println("updateContents.toString() = " + updateContents.toString());

        if (updateContents.isEmpty()) return postDAO.getContent();
        else {
            for (Map<String, String> updateContent : updateContents){

                // postContentId 생성
                Long postContentId = createUniqueIdBean.exec();

                // postContent index
                Integer postContentIndex = Integer.parseInt(updateContent.get("postContentIndex"));

                // 이미지 Url
                String imageUrl = updateContent.get("imageUrl");

                // 내용
                String content = updateContent.get("content");

                // 소프트 딜리트
                boolean deleteCheck = false;

                // postContent 저장
                savePostContentsDAOBean.exec(new PostContentDAO(postContentId, postDAO.getPostId(), imageUrl, content, postContentIndex, deleteCheck));

                Map<Integer, Long> updatePostContentIndex = Collections.singletonMap(postContentIndex, postContentId);

                postContentIndexs.add(updatePostContentIndex);
            }
        }

        // postContent 반환
        String updateContent = "";
        try {
            updateContent = objectMapper.writeValueAsString(postContentIndexs);
        }catch (IOException e){
            e.printStackTrace();
        }

        return updateContent;
    }
}
