package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class SavePostMetaDAOBean {

    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @Autowired
    public SavePostMetaDAOBean(PostMetaRepositoryJPA postMetaRepositoryJPA) {
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
    }

    // Save the PostMeta
    public void exec(PostMeta postMeta){
        postMetaRepositoryJPA.save(postMeta);
    }

    // Create PostMeta
    public void exec(long postId, RequestPostSaveDTO requestPostSaveDTO, List<Map<Integer, Long>> postContentIndex){

        // 유저 아이디
        Long userId = requestPostSaveDTO.getUserId();

        // 제목
        String title = requestPostSaveDTO.getTitle();

        // 내용
        String postContentId = "";
        for (Map<Integer, Long> map : postContentIndex) {
            if (map.containsKey(0)) {
                postContentId = map.get(0).toString();
                break;  // 원하는 값을 찾았으므로 반복문 종료
            }
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

        // soft delete
        boolean deleteCheck = false;

        exec(new PostMeta(postId, userId, title, postContentId, category, uploadTime, updateTime, heartCount, commentCount, viewCount, deleteCheck));
    }
}
