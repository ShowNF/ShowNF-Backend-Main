package com.shownf.reptile.Model.DTO;

import com.shownf.reptile.Model.Enum.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponsePostMetaDTO {
    Long postId;
    Long userId;
    String title;
    String content;
    String category;
    LocalDateTime uploadTime;
    LocalDateTime updateTime;
    Integer heartCount;
    Integer commentCount;
    Integer viewCount;
}
