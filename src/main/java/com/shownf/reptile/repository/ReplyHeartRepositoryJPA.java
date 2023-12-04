package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyHeartRepositoryJPA extends JpaRepository<ReplyHeartDAO, Long> {

    List<ReplyHeartDAO> findByUserId(Long userId);

    ReplyHeartDAO findByUserIdAndReplyId(Long userId, Long replyId);
}
