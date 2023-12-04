package com.shownf.reptile.bean;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.bean.small.GetPostHeartPostIdsDAOBean;
import com.shownf.reptile.bean.small.GetPostHeartsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostIdsBean {

    GetPostHeartsDAOBean getPostHeartsDAOBean;
    GetPostHeartPostIdsDAOBean getPostHeartPostIdsDAOBean;

    @Autowired
    public GetPostIdsBean(GetPostHeartsDAOBean getPostHeartsDAOBean, GetPostHeartPostIdsDAOBean getPostHeartPostIdsDAOBean) {
        this.getPostHeartsDAOBean = getPostHeartsDAOBean;
        this.getPostHeartPostIdsDAOBean = getPostHeartPostIdsDAOBean;
    }

    // 좋아요 누른 게시물 아이디 전체 조회
    public List<Long> exec(Long userId){

        // 유저가 누른 좋아요 가져오기
        List<PostHeartDAO> postHeartDAOS = getPostHeartsDAOBean.exec(userId);

        // 좋아요에서 게시물 id만 가져오기
        return getPostHeartPostIdsDAOBean.exec(postHeartDAOS);
    }
}
