package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
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

    // 마이페이지 유저 QnA 게시물 Page 형태로 전체 조회
    public Page<ResponseQnAPostGetDTO> exec(Long userId, Pageable pageable){

        // 유저 아이디로 QnA 게시물 전체 찾기
        Page<QnAPostDAO> qnAPostDAOs = getQnAPostsDAOBean.exec(userId, pageable);

        // 게시물 삭제 여부 확인
        Page<QnAPostDAO> filteredQnAPostDAOS = checkDeleteQnAPostDAOBean.exec(qnAPostDAOs);

        // DAO 객체 DTO 반환
        return createPostsDTOBean.exec(filteredQnAPostDAOS);
    }
}
