package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetCommentHeartCommentIdsDAOBean {

    // 좋아요 누른 댓글 아이디 가져오기
    public List<Long> exec(List<CommentHeartDAO> commentHeartDAOS){
        List<Long> commentIds = new ArrayList<>();

        for (CommentHeartDAO commentHeartDAO : commentHeartDAOS)
            commentIds.add(commentHeartDAO.getCommentId());

        return commentIds;
    }
}
