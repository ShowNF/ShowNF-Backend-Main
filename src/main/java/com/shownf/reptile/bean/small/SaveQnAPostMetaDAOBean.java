package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.repository.qna.QnAPostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveQnAPostMetaDAOBean {

    QnAPostMetaRepositoryJPA qnAPostMetaRepositoryJPA;

    @Autowired
    public SaveQnAPostMetaDAOBean(QnAPostMetaRepositoryJPA qnAPostMetaRepositoryJPA) {
        this.qnAPostMetaRepositoryJPA = qnAPostMetaRepositoryJPA;
    }

    // QnA 메타데이터 저장
    public void exec(QnAPostMeta qnAPostMeta){
        qnAPostMetaRepositoryJPA.save(qnAPostMeta);
    }

    // QnA 게시물 메타데이터 DAO 생성
    public void exec(long qnaPostId, RequestQnAPostSaveDTO requestQnAPostSaveDTO){

        // 유저 아이디
        Long userId = requestQnAPostSaveDTO.getUserId();

        // 제목
        String title = requestQnAPostSaveDTO.getTitle();

        // 이미지 url
        String imageUrl = requestQnAPostSaveDTO.getImageUrl();

        // 내용
        String content = requestQnAPostSaveDTO.getContent();

        // 업로드 시간
        LocalDateTime uploadTime = LocalDateTime.now();

        // 수정 시간
        LocalDateTime updateTime = LocalDateTime.now();

        // 좋아요 갯수
        Integer heartCount = 0;

        // 댓글 갯수
        Integer commentCount = 0;

        // 조회수
        Integer viewCount = 0;

        // 삭제 여부
        boolean deleteCheck = false;

        exec(new QnAPostMeta(qnaPostId, userId, title, imageUrl, content, uploadTime, updateTime, heartCount, commentCount, viewCount, deleteCheck));
    }
}
