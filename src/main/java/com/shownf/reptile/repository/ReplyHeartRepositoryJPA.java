package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyHeartRepositoryJPA extends JpaRepository<ReplyHeartDAO, Long> {

    ReplyHeartDAO findByUserIdAndReplyId(Long userId, Long replyId);
}
