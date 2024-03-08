package com.shownf.reptile.Model.entity;

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
public class PostLogDAO {
    @Id
    Long postLogId;
    Long postId;
    Long userId;
    LocalDateTime viewTime;
    Integer type; // 0 -> post, // 1 -> QnA post
}
