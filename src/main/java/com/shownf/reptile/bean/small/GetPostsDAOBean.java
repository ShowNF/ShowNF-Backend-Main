package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.Enum.Category;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import com.shownf.reptile.repository.PostRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPostsDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @Autowired
    public GetPostsDAOBean(PostRepositoryJPA postRepositoryJPA, PostMetaRepositoryJPA postMetaRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
    }

    // 게시물 찾기
    public Page<PostMeta> exec(Pageable pageable){
        return postMetaRepositoryJPA.findAll(pageable);
    }

    // 게시물 아이디로 게시물 찾기
    public Page<PostDAO> exec(List<Long> postIds, Pageable pageable) {
        return postRepositoryJPA.findAllByPostIdIn(postIds, pageable);
    }

    // 유저 아이디로 게시물 전체 찾기
    public Page<PostMeta> exec(Long userId, Pageable pageable){
        return postMetaRepositoryJPA.findByUserId(userId, pageable);
    }

    // 카테고리별 게시물 찾기
    public Page<PostMeta> exec(String category, Pageable pageable){
        return postMetaRepositoryJPA.findByCategory(Category.valueOf(category), pageable);
    }
}
