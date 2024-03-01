package com.shownf.reptile.bean;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.bean.small.CheckDeleteQnAPostDAOBean;
import com.shownf.reptile.bean.small.CreateQnAPostsDTOBean;
import com.shownf.reptile.bean.small.GetQnAPostsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetQnAPostsBean {

    GetQnAPostsDAOBean getQnAPostsDAOBean;
    CheckDeleteQnAPostDAOBean checkDeleteQnAPostDAOBean;
    CreateQnAPostsDTOBean createPostsDTOBean;

    @Autowired
    public GetQnAPostsBean(GetQnAPostsDAOBean getQnAPostsDAOBean, CheckDeleteQnAPostDAOBean checkDeleteQnAPostDAOBean, CreateQnAPostsDTOBean createPostsDTOBean) {
        this.getQnAPostsDAOBean = getQnAPostsDAOBean;
        this.checkDeleteQnAPostDAOBean = checkDeleteQnAPostDAOBean;
        this.createPostsDTOBean = createPostsDTOBean;
    }

    // 핫 QnA 게시물 Page 형태로 전체 조회
    public Page<Long> exec(Pageable pageable){

        // 게시물 전체 찾기
        Page<QnAPostMeta> qnAPostMetas = getQnAPostsDAOBean.exec(pageable);

        // 게시물 삭제 여부 확인
        Page<QnAPostMeta> filteredQnAPostMetas = checkDeleteQnAPostDAOBean.exec(qnAPostMetas);

        // DAO 객체 DTO 반환
        return createPostsDTOBean.exec(filteredQnAPostMetas);
    }

    // 마이페이지 유저 QnA 게시물 Page 형태로 전체 조회
    public Page<Long> exec(Long userId, Pageable pageable){

        // 유저 아이디로 QnA 게시물 전체 찾기
        Page<QnAPostMeta> qnAPostMetas = getQnAPostsDAOBean.exec(userId, pageable);

        // 게시물 삭제 여부 확인
        Page<QnAPostMeta> filteredQnAPostMetaS = checkDeleteQnAPostDAOBean.exec(qnAPostMetas);

        // DAO 객체 DTO 반환
        return createPostsDTOBean.exec(filteredQnAPostMetaS);
    }

    // 유저가 좋아요한 QnA 게시물 조회
    // 유저 좋아요 만들고 나서 만들어야함
}
