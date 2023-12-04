package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.CommentHeartDAO;
import com.shownf.reptile.bean.small.GetCommentHeartCommentIdsDAOBean;
import com.shownf.reptile.bean.small.GetCommentHeartsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCommentIdsBean {

    GetCommentHeartsDAOBean getCommentHeartsDAOBean;
    GetCommentHeartCommentIdsDAOBean getCommentHeartCommentIdsDAOBean;

    @Autowired
    public GetCommentIdsBean(GetCommentHeartsDAOBean getCommentHeartsDAOBean, GetCommentHeartCommentIdsDAOBean getCommentHeartCommentIdsDAOBean) {
        this.getCommentHeartsDAOBean = getCommentHeartsDAOBean;
        this.getCommentHeartCommentIdsDAOBean = getCommentHeartCommentIdsDAOBean;
    }

    // 좋아요 누른 댓글 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 누른 좋아요 가져오기
        List<CommentHeartDAO> commentHeartDAOS = getCommentHeartsDAOBean.exec(userId);

        // 좋아요에서 게시물 id만 가져오기
        return getCommentHeartCommentIdsDAOBean.exec(commentHeartDAOS);
    }
}
