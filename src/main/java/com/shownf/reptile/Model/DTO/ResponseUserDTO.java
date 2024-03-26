package com.shownf.reptile.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUserDTO {
    Long userId;
    String oauthId;
    String name;
    String image;
    String siteImage;
    String siteName;
    String email;
    LocalDateTime uploadTime;
    Integer followerCount;
    Integer followingCount;
    Integer postCount;
    Integer commentCount;
    Integer petCount;
    Integer diaryCount;
    Integer sendCommentCount;
    Integer sendHeartCount;
    Integer receiveHeartCount;
    Integer selectionCount;
    Integer saleCount;
    Integer exp;
}
