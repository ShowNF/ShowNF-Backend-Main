package com.shownf.reptile.Model.entity.qna;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QnACommentDAO {
    @Id
    Long qnaCommentId;
    Long qnaPostId;
    Long userId;
    String content;
    String imageUrl;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer replyCount;
    boolean selection;
    boolean deleteCheck;
}
