package com.shownf.reptile.bean;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.qna.QnAPostHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAPostUserHeartBean {

    GetQnAPostHeartDAOBean getPostHeartsDAOBean;
    GetQnAPostHeartInPostIdBean getPostHeartsPostIdBean;
    GetQnAPostsDAOBean getQnAPostsDAOBean;
    CheckDeleteQnAPostDAOBean checkDeleteQnAPostDAOBean;
    CreateQnAPostsDTOBean createQnAPostsDTOBean;

    @Autowired
    public GetQnAPostUserHeartBean(GetQnAPostHeartDAOBean getPostHeartsDAOBean, GetQnAPostHeartInPostIdBean getPostHeartsPostIdBean, GetQnAPostsDAOBean getQnAPostsDAOBean, CheckDeleteQnAPostDAOBean checkDeleteQnAPostDAOBean, CreateQnAPostsDTOBean createQnAPostsDTOBean) {
        this.getPostHeartsDAOBean = getPostHeartsDAOBean;
        this.getPostHeartsPostIdBean = getPostHeartsPostIdBean;
        this.getQnAPostsDAOBean = getQnAPostsDAOBean;
        this.checkDeleteQnAPostDAOBean = checkDeleteQnAPostDAOBean;
        this.createQnAPostsDTOBean = createQnAPostsDTOBean;
    }

    // 유저가 좋아요한 QnA 게시물 조회
    public Page<Long> exec(Long userId, Pageable pageable){

        // 유저 아이디를 통해 QnA 게시물 좋아요 객체 찾기
        List<QnAPostHeartDAO> qnAPostHeartDAOS = getPostHeartsDAOBean.exec(userId);

        // 좋아요 객체에서 QnA 게시물 아이디 찾기
        List<Long> qnaPostIds = getPostHeartsPostIdBean.exec(qnAPostHeartDAOS);

        // 아이디로 QnA 게시물 찾기
        Page<QnAPostMeta> qnAPostMetas = getQnAPostsDAOBean.exec(qnaPostIds, pageable);

        // 게시물 삭제 여부 확인
        Page<QnAPostMeta> newQnAPostMetas = checkDeleteQnAPostDAOBean.exec(qnAPostMetas);

        // DAO 객체 DTO 로 반환
        return createQnAPostsDTOBean.exec(newQnAPostMetas);
    }
}
