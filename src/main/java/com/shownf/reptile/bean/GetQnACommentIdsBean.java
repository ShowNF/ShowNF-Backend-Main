package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.qna.QnACommentHeartDAO;
import com.shownf.reptile.bean.small.GetQnACommentHeartDAOBean;
import com.shownf.reptile.bean.small.GetQnACommentHeartQnACommentIdsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnACommentIdsBean {

    GetQnACommentHeartDAOBean getQnACommentHeartDAOBean;
    GetQnACommentHeartQnACommentIdsDAOBean getQnACommentHeartQnACommentIdsDAOBean;

    @Autowired
    public GetQnACommentIdsBean(GetQnACommentHeartDAOBean getQnACommentHeartDAOBean, GetQnACommentHeartQnACommentIdsDAOBean getQnACommentHeartQnACommentIdsDAOBean) {
        this.getQnACommentHeartDAOBean = getQnACommentHeartDAOBean;
        this.getQnACommentHeartQnACommentIdsDAOBean = getQnACommentHeartQnACommentIdsDAOBean;
    }

    // 좋아요 누른 QnA 댓글 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 누른 좋아요 가져오기
        List<QnACommentHeartDAO> qnACommentHeartDAOS = getQnACommentHeartDAOBean.exec(userId);

        // 좋아요에서 QnA 댓글 id만 가져오기
        return getQnACommentHeartQnACommentIdsDAOBean.exec(qnACommentHeartDAOS);
    }
}
