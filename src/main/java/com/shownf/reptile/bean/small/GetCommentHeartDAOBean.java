package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentHeartSaveDTO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.repository.CommentHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetCommentHeartDAOBean {

    CommentHeartRepositoryJPA commentHeartRepositoryJPA;

    @Autowired
    public GetCommentHeartDAOBean(CommentHeartRepositoryJPA commentHeartRepositoryJPA) {
        this.commentHeartRepositoryJPA = commentHeartRepositoryJPA;
    }

    // 댓글 좋아요 아이디로 삭제할 댓글 찾기
    public CommentHeartDAO exec(Long commentHeartId){
        Optional<CommentHeartDAO> commentHeartOptional = commentHeartRepositoryJPA.findById(commentHeartId);
        return commentHeartOptional.orElse(null);
    }

    // 댓글 좋아요 중복 배제를 위한 댓글 찾기
    public CommentHeartDAO exec(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){
        return commentHeartRepositoryJPA.findByUserIdAndCommentId(requestCommentHeartSaveDTO.getUserId(), requestCommentHeartSaveDTO.getCommentId());
    }

    // 댓글 좋아요 삭제를 위한 객체 찾기
    public CommentHeartDAO exec(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){
        return commentHeartRepositoryJPA.findByUserIdAndCommentId(requestCommentHeartDeleteDTO.getUserId(), requestCommentHeartDeleteDTO.getCommentId());
    }
}
