package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostUpdateDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdatePostDAOBean {

    // Update the post
    public PostDAO exec(String updateContent, RequestPostUpdateDTO requestPostUpdateDTO, PostDAO postDAO){

        // 카테고리
        postDAO.setCategory(Category.valueOf(requestPostUpdateDTO.getCategory()));

        // 제목
        postDAO.setTitle(requestPostUpdateDTO.getTitle());

        // 내용
        postDAO.setContent(updateContent);

        // 수정시간
        postDAO.setUpdateTime(LocalDateTime.now());

        return postDAO;
    }
}
