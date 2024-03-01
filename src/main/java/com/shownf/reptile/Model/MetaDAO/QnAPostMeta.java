package com.shownf.reptile.Model.MetaDAO;

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
public class QnAPostMeta {
    @Id
    Long qnaPostId;
    Long userId;
    String title;
    String imageUrl;
    String content;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer commentCount;
    Integer viewCount;
    boolean deleteCheck;
}
