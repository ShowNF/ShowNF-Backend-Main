package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.bean.small.CreatePostsDTOBean;
import com.shownf.reptile.bean.small.DeleteCheckPostDAOBean;
import com.shownf.reptile.bean.small.GetPostsDAOBean;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetPostsBean {

    GetPostsDAOBean getPostsDAOBean;
    DeleteCheckPostDAOBean deleteCheckPostDAOBean;
    CreatePostsDTOBean createPostsDTOBean;

    @Autowired
    public GetPostsBean(GetPostsDAOBean getPostsDAOBean, DeleteCheckPostDAOBean deleteCheckPostDAOBean, CreatePostsDTOBean createPostsDTOBean) {
        this.getPostsDAOBean = getPostsDAOBean;
        this.deleteCheckPostDAOBean = deleteCheckPostDAOBean;
        this.createPostsDTOBean = createPostsDTOBean;
    }

    // 핫 게시물 Page 형태로 전체 조회
    public Page<RequestPostDTO> exec(Pageable pageable){

        // 게시물 전체 찾기
        Page<PostDAO> postDAOs = getPostsDAOBean.exec(pageable);

        // DAO 객체 DTO 반환
        return createPostsDTOBean.exec(pageable, postDAOs);
    }

    // 마이페이지 유저 게시물 Page 형태로 전체 조회
    public Page<Long> exec(Long userId, Pageable pageable){

        // 유저 아이디로 게시물 전체 찾기 - 메타데이터
        Page<PostMeta> postMetas = getPostsDAOBean.exec(userId, pageable);

        // 게시물 삭제 여부 확인
        Page<PostMeta> newPostMetas = deleteCheckPostDAOBean.exec(postMetas);

        // DAO 객체 DTO 반환
        return createPostsDTOBean.exec(userId, pageable, newPostMetas);
    }

}
