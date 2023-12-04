package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetPostHeartPostIdsDAOBean {

    // 좋아요 눌린 게시물 아이디 가져오기
    public List<Long> exec(List<PostHeartDAO> postHeartDAOS){

        List<Long> postIds = new ArrayList<>();

        for (PostHeartDAO postHeartDAO : postHeartDAOS)
            postIds.add(postHeartDAO.getPostId());

        return postIds;
    }
}
