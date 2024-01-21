package com.shownf.reptile.Model.MetaDAO;

import com.shownf.reptile.Model.Enum.Category;
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
public class PostMeta {
    @Id
    Long postId;
    Long userId;
    String title;
    String content;
    Category category;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer commentCount;
    Integer viewCount;
}
