package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.repository.PostMetaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetPostMetaDAOBean {

    PostMetaRepositoryJPA postMetaRepositoryJPA;

    @Autowired
    public GetPostMetaDAOBean(PostMetaRepositoryJPA postMetaRepositoryJPA) {
        this.postMetaRepositoryJPA = postMetaRepositoryJPA;
    }

    // Get the post meta
    public PostMeta exec(Long postId){
        return postMetaRepositoryJPA.findById(postId).orElse(null);
    }

    // Get post metas
    public List<PostMeta> exec(List<Long> postIds){
        return postMetaRepositoryJPA.findAllById(postIds);
    }

    // 좋아요순으로 추천 게시글 4개 가져오기
    public List<PostMeta> exec(){
        return postMetaRepositoryJPA.findTop4ByOrderByHeartCountDesc();
    }

    // 검색어를 기준으로 게시물을 조회하는 - 좋아요순
    public List<PostMeta> exec(String search) {

        // postMetaRepositoryJPA를 사용하여 검색어를 포함하는 사용자를 조회
        List<PostMeta> postMetas = postMetaRepositoryJPA.findAllByOrderByHeartCountDesc();

        // 검색어를 포함하는 사용자만 필터링하여 반환
        return postMetas.stream()
                .filter(postMeta -> postMeta.getTitle().contains(search))
                .collect(Collectors.toList());
    }

    // 검색어를 기준으로 게시물을 조회하는 메서드 - 최신순
    public List<PostMeta> exec(String search, String searchType) {

        // postMetaRepositoryJPA를 사용하여 검색어를 포함하는 사용자를 조회
        List<PostMeta> postMetas = postMetaRepositoryJPA.findAllByOrderByUploadTimeDesc();


        // 검색어를 포함하는 사용자만 필터링하여 반환
        return postMetas.stream()
                .filter(postMeta -> postMeta.getTitle().contains(search))
                .collect(Collectors.toList());
    }
}
