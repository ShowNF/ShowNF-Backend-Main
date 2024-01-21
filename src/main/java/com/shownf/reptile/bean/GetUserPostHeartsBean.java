package com.shownf.reptile.bean;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserPostHeartsBean {

    GetPostHeartsDAOBean getPostHeartsDAOBean;
    GetPostHeartsPostIdBean getPostHeartsPostIdBean;
    GetPostsDAOBean getPostsDAOBean;
    DeleteCheckPostDAOBean deleteCheckPostDAOBean;
    CreatePostsDTOBean createPostsDTOBean;

    @Autowired
    public GetUserPostHeartsBean(GetPostHeartsDAOBean getPostHeartsDAOBean, GetPostHeartsPostIdBean getPostHeartsPostIdBean, GetPostsDAOBean getPostsDAOBean, DeleteCheckPostDAOBean deleteCheckPostDAOBean, CreatePostsDTOBean createPostsDTOBean) {
        this.getPostHeartsDAOBean = getPostHeartsDAOBean;
        this.getPostHeartsPostIdBean = getPostHeartsPostIdBean;
        this.getPostsDAOBean = getPostsDAOBean;
        this.deleteCheckPostDAOBean = deleteCheckPostDAOBean;
        this.createPostsDTOBean = createPostsDTOBean;
    }

    // 유저가 좋아요한 게시물 조회
    public Page<Long> exec(Long userId, Pageable pageable){

        // 유저 아이디를 통해 게시물 좋아요 객체 찾기
        List<PostHeartDAO> postHeartDAOs = getPostHeartsDAOBean.exec(userId);

        // 좋아요 객체에서 게시물 아이디 찾기
        List<Long> postIds = getPostHeartsPostIdBean.exec(postHeartDAOs);

        // 게시물 아이디로 게시물 찾기
        Page<PostMeta> postMetas = getPostsDAOBean.exec(postIds, pageable);

        // 게시물 삭제 여부 확인
        Page<PostMeta> newPostMetas = deleteCheckPostDAOBean.exec(postMetas);

        // DAO 객체 DTO 로 반환
        return createPostsDTOBean.exec(pageable, newPostMetas);
    }
}
