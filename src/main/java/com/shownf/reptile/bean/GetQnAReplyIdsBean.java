package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.qna.QnAReplyHeartDAO;
import com.shownf.reptile.bean.small.GetQnAReplyHeartDAOBean;
import com.shownf.reptile.bean.small.GetQnAReplyHeartQnAReplyIdsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAReplyIdsBean {

    GetQnAReplyHeartDAOBean getQnAReplyHeartDAOBean;
    GetQnAReplyHeartQnAReplyIdsDAOBean getQnAReplyHeartQnAReplyIdsDAOBean;

    @Autowired
    public GetQnAReplyIdsBean(GetQnAReplyHeartDAOBean getQnAReplyHeartDAOBean, GetQnAReplyHeartQnAReplyIdsDAOBean getQnAReplyHeartQnAReplyIdsDAOBean) {
        this.getQnAReplyHeartDAOBean = getQnAReplyHeartDAOBean;
        this.getQnAReplyHeartQnAReplyIdsDAOBean = getQnAReplyHeartQnAReplyIdsDAOBean;
    }

    // 좋아요 누른 QnA 대댓글 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 누른 좋아요 가져오기
        List<QnAReplyHeartDAO> postHeartDAOS = getQnAReplyHeartDAOBean.exec(userId);

        // 좋아요에서 QnA 대댓글 id만 가져오기
        return getQnAReplyHeartQnAReplyIdsDAOBean.exec(postHeartDAOS);
    }
}
