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
public class QnAReplyHeartDAO {
    @Id
    Long qnaReplyHeartId;
    Long qnaReplyId;
    Long userId;
    LocalDateTime uploadTime;
}
