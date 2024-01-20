package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.DTO.RequestPostUpdateDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class UpdatePostDAOBean {

    // Update the post
    public PostDAO exec(List<Map<Integer, Long>> updateContent, RequestPostUpdateDTO requestPostUpdateDTO, PostDAO postDAO){

        // 카테고리
        postDAO.setCategory(Category.valueOf(requestPostUpdateDTO.getCategory()));

        // 제목
        postDAO.setTitle(requestPostUpdateDTO.getTitle());

        // 내용
        ObjectMapper objectMapper = new ObjectMapper();
        String content = "";
        try {
            content = objectMapper.writeValueAsString(updateContent);
        }catch (IOException e){
            e.printStackTrace();
        }
        postDAO.setContent(content);

        // 수정시간
        postDAO.setUpdateTime(LocalDateTime.now());

        return postDAO;
    }

    // Update the postMeta
    public PostMeta exec(List<Map<Integer, Long>> updateContent, RequestPostUpdateDTO requestPostUpdateDTO, PostMeta postMeta){

        // 카테고리
        postMeta.setCategory(Category.valueOf(requestPostUpdateDTO.getCategory()));

        // 제목
        postMeta.setTitle(requestPostUpdateDTO.getTitle());

        // 내용
        String postContentId = "";
        for (Map<Integer, Long> map : updateContent) {
            if (map.containsKey(0)) {
                postContentId = map.get(0).toString();
                break;  // 원하는 값을 찾았으므로 반복문 종료
            }
        }
        postMeta.setContent(postContentId);

        // 수정시간
        postMeta.setUpdateTime(LocalDateTime.now());

        return postMeta;
    }
}
