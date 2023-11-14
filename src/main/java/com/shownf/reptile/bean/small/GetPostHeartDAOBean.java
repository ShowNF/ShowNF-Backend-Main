package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestPostHeartSaveDTO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import com.shownf.reptile.Model.entity.ReplyHeartDAO;
import com.shownf.reptile.repository.PostHeartRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetPostHeartDAOBean {

    PostHeartRepositoryJPA postHeartRepositoryJPA;

    @Autowired
    public GetPostHeartDAOBean(PostHeartRepositoryJPA postHeartRepositoryJPA) {
        this.postHeartRepositoryJPA = postHeartRepositoryJPA;
    }

    // 좋아요 객체 찾기
    public PostHeartDAO exec(Long postHeartId){
        Optional<PostHeartDAO> postHeartOptional = postHeartRepositoryJPA.findById(postHeartId);
        return postHeartOptional.orElse(null);
    }

    // 게시물 좋아요 중복 배제를 위한 객체 찾기
    public PostHeartDAO exec(RequestPostHeartSaveDTO requestPostHeartSaveDTO){
        return postHeartRepositoryJPA.findByUserIdAndPostId(requestPostHeartSaveDTO.getUserId(), requestPostHeartSaveDTO.getPostId());
    }
}
