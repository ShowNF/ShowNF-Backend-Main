package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.Model.DTO.ResponsePostsDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreatePostsDTOBean {

    GetPostContentDAOsBean getPostContentDAOsBean;

    @Autowired
    public CreatePostsDTOBean(GetPostContentDAOsBean getPostContentDAOsBean) {
        this.getPostContentDAOsBean = getPostContentDAOsBean;
    }

    // 게시물 조회시 postId 리스트 생성
    public Page<Long> exec(Pageable pageable, Page<PostMeta> postMetas){

        List<Long> postIds = new ArrayList<>();

        // DTO 객체에 게시물 정보 넘기기
        for (PostMeta postMeta: postMetas) {
            postIds.add(postMeta.getPostId());
        }

        // List 구조를 Page 구조로 변경 후 반환
        return new PageImpl<>(postIds, pageable, postMetas.getTotalElements());
    }

    // 카테고리별 게시물 조회시 DTO 생성
    public Page<RequestPostDTO> exec(String category, Pageable pageable, Page<PostDAO> postDAOs){

        List<RequestPostDTO> requestPostDTOs = new ArrayList<>();

        // DTO 객체에 게시물 정보 넘기기
        for (PostDAO postDAO: postDAOs) {
            RequestPostDTO requestPostDTO = new RequestPostDTO();

            requestPostDTO.setPostId(postDAO.getPostId());
            requestPostDTO.setUserId(postDAO.getUserId());
            requestPostDTO.setTitle(postDAO.getTitle());

            String content = getPostContentDAOsBean.exec(postDAO.getPostId(), postDAO.getContent());
            requestPostDTO.setContent(content);
            requestPostDTO.setCategory(postDAO.getCategory().name());
            requestPostDTO.setUploadTime(postDAO.getUploadTime());
            requestPostDTO.setUpdateTime(postDAO.getUpdateTime());
            requestPostDTO.setHeartCount(postDAO.getHeartCount());
            requestPostDTO.setCommentCount(postDAO.getCommentCount());
            requestPostDTO.setViewCount(postDAO.getViewCount());

            requestPostDTOs.add(requestPostDTO);
        }

        // List 구조를 Page 구조로 변경 후 반환
        return new PageImpl<>(requestPostDTOs, pageable, postDAOs.getTotalElements());
    }

    // 마이페이지 유저 게시물 조회시 DTO 생성
    public Page<Long> exec(Long userId, Pageable pageable, Page<PostMeta> postMetas){

        List<Long> postIds = new ArrayList<>();

        // DTO 객체에 게시물 정보 넘기기
        for (PostMeta postMeta: postMetas) {
            postIds.add(postMeta.getPostId());
        }

        // List 구조를 Page 구조로 변경 후 반환
        return new PageImpl<>(postIds, pageable, postMetas.getTotalElements());
    }

    // 게시물 아이디로 게시물 조회시 DTO 생성
    public Page<ResponsePostsDTO> exec(Page<PostDAO> postDAOs, Pageable pageable){

        List<ResponsePostsDTO> responsePostsDTOs = new ArrayList<>();

        // DTO 객체에 게시물 정보 넘기기
        for (PostDAO postDAO: postDAOs) {
            ResponsePostsDTO responsePostsDTO = new ResponsePostsDTO();

            responsePostsDTO.setPostId(postDAO.getPostId());
            responsePostsDTO.setUserId(postDAO.getUserId());
            responsePostsDTO.setTitle(postDAO.getTitle());

            String content = getPostContentDAOsBean.exec(postDAO.getPostId(), postDAO.getContent());
            responsePostsDTO.setContent(content);
            responsePostsDTO.setCategory(postDAO.getCategory().name());
            responsePostsDTO.setUploadTime(postDAO.getUploadTime());
            responsePostsDTO.setUpdateTime(postDAO.getUpdateTime());
            responsePostsDTO.setHeartCount(postDAO.getHeartCount());
            responsePostsDTO.setCommentCount(postDAO.getCommentCount());
            responsePostsDTO.setViewCount(postDAO.getViewCount());

            responsePostsDTOs.add(responsePostsDTO);
        }

        // DTO 반환
        return new PageImpl<>(responsePostsDTOs, pageable, postDAOs.getTotalElements());
    }
}
