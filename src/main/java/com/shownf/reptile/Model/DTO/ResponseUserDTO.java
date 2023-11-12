package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUserDTO {
    Long handleId;
    String userId;
    String name;
    String image;
    String siteImage;
    String siteName;
    LocalDateTime uploadTime;
    Integer followerCount;
    Integer followingCount;
    Integer postCount;
    Integer heartCount;
    Integer commentCount;
}
