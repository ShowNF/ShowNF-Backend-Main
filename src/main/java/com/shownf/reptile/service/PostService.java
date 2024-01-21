package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.*;
import com.shownf.reptile.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class PostService {

    GetPostBean getPostBean;
    GetPostsBean getPostsBean;
    GetCategoryPostsBean categoryPostsBean;
    GetUserPostHeartsBean getUserPostHeartsBean;
    SavePostBean savePostBean;
    UpdatePostBean updatePostBean;
    DeletePostBean deletePostBean;

    @Autowired
    public PostService(GetPostBean getPostBean, GetPostsBean getPostsBean, GetCategoryPostsBean categoryPostsBean, GetUserPostHeartsBean getUserPostHeartsBean, SavePostBean savePostBean, UpdatePostBean updatePostBean, DeletePostBean deletePostBean) {
        this.getPostBean = getPostBean;
        this.getPostsBean = getPostsBean;
        this.categoryPostsBean = categoryPostsBean;
        this.getUserPostHeartsBean = getUserPostHeartsBean;
        this.savePostBean = savePostBean;
        this.updatePostBean = updatePostBean;
        this.deletePostBean = deletePostBean;
    }

    // 게시물 조회
    public RequestPostDTO getPostDAO(Long postId, Long userId){
        return getPostBean.exec(postId, userId);
    }

    // 마이페이지 유저 게시물 전체 조회
    public Page<Long> getPosts(Long userId, Pageable pageable){
        return getPostsBean.exec(userId, pageable);
    }

    // 핫 게시물 조회
    public Page<RequestPostDTO> getHotPosts(Pageable pageable){
        return getPostsBean.exec(pageable);
    }

    // 카테고리별 게시물 조회
    public Page<RequestPostDTO> getCategoryPosts(String category, Pageable pageable){
        return categoryPostsBean.exec(category, pageable);
    }

    // 유저가 좋아요한 게시물 조회
    public Page<ResponsePostsDTO> getUserPostHearts(Long userId, Pageable pageable){
        return getUserPostHeartsBean.exec(userId, pageable);
    }

    // 게시물 저장
    public Long savePostDAO(RequestPostSaveDTO requestPostSaveDTO){
        return savePostBean.exec(requestPostSaveDTO);
    }

    // 게시물 수정
    public Long updatePostDAO(RequestPostUpdateDTO requestPostUpdateDTO, HttpServletRequest request){
        return updatePostBean.exec(requestPostUpdateDTO, request);
    }

    // 게시물 삭제
    public Long deletePostDAO(RequestPostDeleteDTO requestPostDeleteDTO, HttpServletRequest request){ return deletePostBean.exec(requestPostDeleteDTO, request); }

}
