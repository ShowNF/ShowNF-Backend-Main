package com.shownf.reptile.bean.small;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class SavePostDAOBean {

    PostRepositoryJPA postRepositoryJPA;

    @Autowired
    public SavePostDAOBean(PostRepositoryJPA postRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
    }

    // 게시물 저장하기
    public void exec(PostDAO postDAO){
        postRepositoryJPA.save(postDAO);
    }

    // 게시물 저장시 DAO 생성
    public void exec(long postId, RequestPostSaveDTO requestPostSaveDTO, List<Map<Integer, Long>> postContentIndex){

        // 유저 아이디
        Long userId = requestPostSaveDTO.getUserId();

        // 제목
        String title = requestPostSaveDTO.getTitle();

        // 내용
        ObjectMapper objectMapper = new ObjectMapper();
        String content = "";
        try {
            content = objectMapper.writeValueAsString(postContentIndex);
        }catch (IOException e){
            e.printStackTrace();
        }

        // 카테고리
        Category category = Category.valueOf(requestPostSaveDTO.getCategory());

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 수정 시간
        LocalDateTime updateTime = LocalDateTime.now();

        // 게시물 좋아요 갯수
        Integer heartCount = 0;

        // 게시물 댓글 갯수
        Integer commentCount = 0;

        // 조회수
        Integer viewCount = 0;

        // 삭제 여부
        boolean deleteCheck = false;

        exec(new PostDAO(postId, userId, title, content, category, uploadTime, updateTime, heartCount, commentCount, viewCount, deleteCheck));
    }
}
