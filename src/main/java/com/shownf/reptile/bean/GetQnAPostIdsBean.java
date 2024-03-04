package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.bean.small.GetQnAPostHeartDAOBean;
import com.shownf.reptile.bean.small.GetQnAPostHeartQnAPostIdsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAPostIdsBean {

    GetQnAPostHeartDAOBean getQnAPostHeartsDAOBean;
    GetQnAPostHeartQnAPostIdsDAOBean getQnAPostHeartPostIdsDAOBean;

    @Autowired
    public GetQnAPostIdsBean(GetQnAPostHeartDAOBean getQnAPostHeartsDAOBean, GetQnAPostHeartQnAPostIdsDAOBean getQnAPostHeartPostIdsDAOBean) {
        this.getQnAPostHeartsDAOBean = getQnAPostHeartsDAOBean;
        this.getQnAPostHeartPostIdsDAOBean = getQnAPostHeartPostIdsDAOBean;
    }

    // 좋아요 누른 QnA 게시물 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 누른 좋아요 가져오기
        List<QnAPostHeartDAO> postHeartDAOS = getQnAPostHeartsDAOBean.exec(userId);

        // 좋아요에서 QnA 게시물 id만 가져오기
        return getQnAPostHeartPostIdsDAOBean.exec(postHeartDAOS);
    }
}
