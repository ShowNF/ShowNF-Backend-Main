package com.shownf.reptile.Model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDAO {
    @Id
    Long userId;
    String oauthId;
    String name;
    String image;
    String siteImage;
    String siteName;
    LocalDateTime uploadTime;
    Integer followerCount;
    Integer followingCount;
}
