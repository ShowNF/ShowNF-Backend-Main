package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.repository.CommentHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCommentHeartsDAOBean {

    CommentHeartRepositoryJPA commentHeartRepositoryJPA;

    @Autowired
    public GetCommentHeartsDAOBean(CommentHeartRepositoryJPA commentHeartRepositoryJPA) {
        this.commentHeartRepositoryJPA = commentHeartRepositoryJPA;
    }

    // 유저가 누른 좋아요 찾기
    public List<CommentHeartDAO> exec(Long userId){
        return commentHeartRepositoryJPA.findByUserId(userId);
    }
}
