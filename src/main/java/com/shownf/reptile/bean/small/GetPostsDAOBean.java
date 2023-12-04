package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPostsDAOBean {

    PostRepositoryJPA postRepositoryJPA;

    @Autowired
    public GetPostsDAOBean(PostRepositoryJPA postRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
    }

    // 게시물 찾기
    public Page<PostDAO> exec(Pageable pageable){
        return postRepositoryJPA.findAll(pageable);
    }

    // 게시물 아이디로 게시물 찾기
    public Page<PostDAO> exec(List<Long> postIds, Pageable pageable) {
        return postRepositoryJPA.findAllByPostIdIn(postIds, pageable);
    }

    // 유저 아이디로 게시물 전체 찾기
    public Page<PostDAO> exec(Long userId, Pageable pageable){
        return postRepositoryJPA.findByUserId(userId, pageable);
    }
}
