package com.shownf.reptile.bean;


import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.bean.small.GetReplyHeartReplyIdsDAOBean;
import com.shownf.reptile.bean.small.GetReplyHeartsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReplyIdsBean {

    GetReplyHeartsDAOBean getReplyHeartsDAOBean;
    GetReplyHeartReplyIdsDAOBean getReplyHeartReplyIdsDAOBean;

    @Autowired
    public GetReplyIdsBean(GetReplyHeartsDAOBean getReplyHeartsDAOBean, GetReplyHeartReplyIdsDAOBean getReplyHeartReplyIdsDAOBean) {
        this.getReplyHeartsDAOBean = getReplyHeartsDAOBean;
        this.getReplyHeartReplyIdsDAOBean = getReplyHeartReplyIdsDAOBean;
    }

    // 좋아요 누른 게시물 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 누른 대댓글 가져오기
        List<ReplyHeartDAO> replyHeartDAOS = getReplyHeartsDAOBean.exec(userId);

        // 좋아요에서 대댓글 id만 가져오기
        return getReplyHeartReplyIdsDAOBean.exec(replyHeartDAOS);
    }
}
