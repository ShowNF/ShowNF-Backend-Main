package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
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
}
